package sbb.utils

import com.github.nscala_time.time.Imports._
import ujson.Value

object Time:
    def now(): Time = Time(DateTime.now().hour.get(), DateTime.now().minute.get())

    def decode_duration(time: Value): Option[Time] =
        if time.isNull then None
        else Some(
            Time(
                time.str.substring(3, 5).toInt + time.str.substring(0, 2).toInt * 24,
                time.str.substring(6, 8).toInt
            )
        )
    
class Time(val hour: Int, val minute: Int) extends DateTimeTrait:
    val hh: String = if (hour < 10) "0" + hour.toString() else hour.toString()
    val mm: String = if (minute < 10) "0" + minute.toString() else minute.toString()

    override def toString(): String = s"${hh}:${mm}"

    def compare(that: DateTimeTrait): Int = 
        if (that.isInstanceOf[Time]) this.compare_time(that.asInstanceOf[Time])
        else throw new Exception("Time.compare(that: DateTimeTrait): Int: that is not a Time or DateTime")

    def compare_time(that: Time): Int = 
        if (this.hour < that.hour) -1
        else if (this.hour > that.hour) 1
        else if (this.minute < that.minute) -1
        else if (this.minute > that.minute) 1
        else 0 
        
    def duration(that: Time): Time =
        val thisMinutes = this.hour * 60 + this.minute
        val thatMinutes = that.hour * 60 + that.minute
        val durationMinutes = thatMinutes - thisMinutes
        val durationHours = durationMinutes / 60
        val durationMinutesRemainder = durationMinutes % 60
        Time(durationHours, durationMinutesRemainder)
        