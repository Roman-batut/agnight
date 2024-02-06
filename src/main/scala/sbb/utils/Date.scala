package sbb.utils

import com.github.nscala_time.time.Imports._

object Date:
    def now(): Date = Date(DateTime.now().year.get(), DateTime.now().month.get(), DateTime.now().day.get())

class Date(val year: Int, val month: Int, val day: Int) extends DateTimeTrait:
    val yyyy: String = year.toString()
    val mm: String = if (month < 10) "0" + month.toString() else month.toString()
    val dd: String = if (day < 10) "0" + day.toString() else day.toString()

    override def toString(): String = s"${yyyy}-${mm}-${dd}"

    def compare(that: DateTimeTrait): Int = 
        if (that.isInstanceOf[Date]) this.compare_date(that.asInstanceOf[Date])
        else throw new Exception("Date.compare(that: DateTimeTrait): Int: that is not a Date or DateTime")

    def compare_date(that: Date): Int = 
        if (this.year < that.year) -1
        else if (this.year > that.year) 1
        else if (this.month < that.month) -1
        else if (this.month > that.month) 1
        else if (this.day < that.day) -1
        else if (this.day > that.day) 1
        else 0
    
    