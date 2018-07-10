package rukiasoft.com.androidutilslibrary.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.provider.CalendarContract
import androidx.annotation.RequiresPermission
import timber.log.Timber
import java.util.*
import javax.inject.Inject


/**
Copyright (C) Rukiasoft - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Raul <raulfeliz@gmail.com>, July 2018
 *
 *
 */

@Suppress("unused")
class CalendarUtils @Inject constructor(private val context: Context) {


    @SuppressLint("MissingPermission")
    @RequiresPermission(Manifest.permission.WRITE_CALENDAR)
    fun addEvent(idCalendar: String, start: EventDate, end: EventDate, title: String, description: String?): String? {
        return addEventInternal(idCalendar, start, end, title, description, false)
    }

    @SuppressLint("MissingPermission")
    @RequiresPermission(Manifest.permission.WRITE_CALENDAR)
    fun addEventAllDay(idCalendar: String, start: EventDate?, title: String, description: String?): String? {
        start?.let {
            val allDayStart = EventDate(start.year, start.month, start.day, 0, 0)
            val allDayEnd = EventDate(start.year, start.month, start.day + 1, 0, 0)
            return addEventInternal(idCalendar, allDayStart, allDayEnd, title, description, true)
        }
        return null
    }

    @SuppressLint("MissingPermission")
    @RequiresPermission(Manifest.permission.WRITE_CALENDAR)
    private fun addEventInternal(idCalendar: String, start: EventDate, end: EventDate, title: String, description: String?, allDay: Boolean): String? {

        val cr = context.contentResolver

        val beginTime = Calendar.getInstance()
        beginTime.set(start.year, start.month, start.day, start.hour, start.minute)

        val endTime = Calendar.getInstance()
        endTime.set(end.year, end.month, end.day, end.hour, end.minute)

        val values = ContentValues()
        values.put(CalendarContract.Events.DTSTART, beginTime.timeInMillis)
        values.put(CalendarContract.Events.DTEND, endTime.timeInMillis)
        values.put(CalendarContract.Events.ALL_DAY, allDay)
        values.put(CalendarContract.Events.TITLE, title)
        description?.let { values.put(CalendarContract.Events.DESCRIPTION, description) }
        values.put(CalendarContract.Events.CALENDAR_ID, idCalendar)
        values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id)

        try {
            val uri = cr.insert(CalendarContract.Events.CONTENT_URI, values)
            uri?.let {
                return uri.lastPathSegment
            }
        } catch (e: Exception) {
            Timber.e("Cannot set the reminder")
        }
        return null
    }

    @RequiresPermission(Manifest.permission.READ_CALENDAR)
    fun getPossibleCalendars(c: Context): List<MyCalendar> {

        val projection = mutableListOf(CalendarContract.Calendars._ID,
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL)

        val contentResolver = c.contentResolver
        val managedCursor = contentResolver.query(CalendarContract.Calendars.CONTENT_URI, projection.toTypedArray(), null, null, null)

        val listOfMyCalendars = mutableListOf<MyCalendar>()
        managedCursor?.count?.let {

            if (managedCursor.moveToFirst()) {

                var calName = ""
                var calID = ""
                var accessLevel = ""

                val nameCol = managedCursor.getColumnIndex(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME)
                val idCol = managedCursor.getColumnIndex(CalendarContract.Calendars._ID)
                val accessLevelCol = managedCursor.getColumnIndex(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL)
                do {
                    if (nameCol >= 0) {
                        calName = managedCursor.getString(nameCol)
                    }
                    if (idCol >= 0) {
                        calID = managedCursor.getString(idCol)
                    }
                    if (accessLevelCol >= 0) {
                        accessLevel = managedCursor.getString(accessLevelCol)
                    }
                    listOfMyCalendars.add(MyCalendar(calName, calID, accessLevel))

                } while (managedCursor.moveToNext())
                managedCursor.close()
            }
            if (listOfMyCalendars.isNotEmpty()) {
                return listOfMyCalendars.filter {
                    try {
                        val access = it.calendarAccess.toInt()
                        access == CalendarContract.Calendars.CAL_ACCESS_EDITOR ||
                                access == CalendarContract.Calendars.CAL_ACCESS_OWNER ||
                                access == CalendarContract.Calendars.CAL_ACCESS_ROOT
                    } catch (e: NumberFormatException) {
                        false
                    }
                }
            }

        }
        return listOfMyCalendars
    }

    @RequiresPermission(Manifest.permission.READ_CALENDAR)
    fun getReminderFromCalendar(idEvent: String): MyEvent? {

        val mProjection = mutableListOf(CalendarContract.Events._ID,
                CalendarContract.Events.TITLE,
                CalendarContract.Events.DTSTART,
                CalendarContract.Events.DTEND
        )

        val uri = CalendarContract.Events.CONTENT_URI
        val selection = CalendarContract.Events._ID + " = ? "
        val selectionArgs = mutableListOf(idEvent)

        val cur = context.contentResolver.query(uri, mProjection.toTypedArray(), selection, selectionArgs.toTypedArray(), null)

        var event: MyEvent? = null
        while (cur.moveToNext()) {

            val id = if (cur.getColumnIndex(CalendarContract.Events._ID) >= 0) {
                cur.getString(cur.getColumnIndex(CalendarContract.Events._ID))
            } else {
                null
            }
            val title = if (cur.getColumnIndex(CalendarContract.Events.TITLE) >= 0) {
                cur.getString(cur.getColumnIndex(CalendarContract.Events.TITLE))
            } else {
                ""
            }
            val start = if (cur.getColumnIndex(CalendarContract.Events.DTSTART) >= 0) {
                cur.getString(cur.getColumnIndex(CalendarContract.Events.DTSTART))
            } else {
                null
            }
            val end = if (cur.getColumnIndex(CalendarContract.Events.DTEND) >= 0) {
                cur.getString(cur.getColumnIndex(CalendarContract.Events.DTEND))
            } else {
                null
            }
            if (id != null && start != null && end != null) {
                try {
                    val startDate = Date(start.toLong())
                    val endDate = Date(end.toLong())
                    event = MyEvent(id, title, startDate, endDate)
                } catch (e: NumberFormatException) {

                }
            }
        }
        cur.close()
        return event

    }

    @SuppressLint("MissingPermission")
    @RequiresPermission(Manifest.permission.WRITE_CALENDAR)
    fun deleteEvent(id: String): Boolean {

        val uri = CalendarContract.Events.CONTENT_URI

        val mSelectionClause = CalendarContract.Events._ID + " = ?"
        val mSelectionArgs = mutableListOf(id)

        val updCount = context.contentResolver.delete(uri, mSelectionClause, mSelectionArgs.toTypedArray())
        return updCount > 0

    }

    inner class MyCalendar constructor(val name: String, val id: String, val calendarAccess: String)
    inner class MyEvent constructor(val id: String, val title: String, val start: Date, val end: Date)

    class EventDate constructor(val year: Int, val month: Int, val day: Int, val hour: Int, val minute: Int)
}