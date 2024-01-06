package utils

class Time(hhmm: String):
    private val hour: Int = hhmm.split(":")(0).toInt
    private val minute: Int = hhmm.split(":")(1).toInt

    def getHour(): Int = hour
    def getMinute(): Int = minute

    override def toString(): String = hour + ":" + minute

    def compare(that: Time): Int =
        if (this.hour < that.hour) -1
        else if (this.hour > that.hour) 1
        else if (this.minute < that.minute) -1
        else if (this.minute > that.minute) 1
        else 0

    def isBefore(that: Time): Boolean =
        if (this.compare(that) < 0) true
        else false

    def isAfter(that: Time): Boolean =
        if (this.compare(that) > 0) true
        else false

    def isEqual(that: Time): Boolean =
        if (this.compare(that) == 0) true
        else false

    def isBetweenStrict(start: Time, end: Time): Boolean =
        if (this.isAfter(start) && this.isBefore(end)) true
        else false

    def isBetweenEqual(start: Time, end: Time): Boolean =
        if (this.isAfter(start) && this.isBefore(end) || this.isEqual(start) || this.isEqual(end)) true
        else false

    def duration(that: Time): Time =
        val hour = that.hour - this.hour
        val minute = that.minute - this.minute
        if (minute < 0) Time(hour - 1 + ":" + minute + 60)
        else Time(hour + ":" + minute)