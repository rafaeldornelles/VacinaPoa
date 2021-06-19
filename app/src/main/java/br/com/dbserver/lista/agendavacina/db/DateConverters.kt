package br.com.dbserver.lista.agendavacina.db

import androidx.room.TypeConverter
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class DateConverters {
    @TypeConverter
    fun dateToString(date: LocalDate): String{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return date.format(formatter)
    }

    @TypeConverter
    fun stringToDate(date: String): LocalDate {
        return LocalDate.parse(date)
    }

    @TypeConverter
    fun timeToString(time:LocalTime): String{
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return time.format(formatter)
    }

    @TypeConverter
    fun stringToTime(time:String): LocalTime{
        return LocalTime.parse(time)
    }

    @TypeConverter
    fun durationToInt(duration: Duration): Int{
        return duration.toDays().toInt()
    }

    @TypeConverter
    fun intToDuration(duration: Int): Duration{
        return Duration.ofDays(duration.toLong())
    }
}
