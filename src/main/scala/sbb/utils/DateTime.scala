package sbb.utils

import ujson.Value

object DateTime:
    def now(): DateTime = DateTime(Date.now(), Time.now())

    def decode(dateTime: Value): Option[DateTime] = 
        if dateTime.isNull then None
        else Some(
            DateTime(
                Date(
                    dateTime.str.substring(0, 4).toInt,
                    dateTime.str.substring(5, 7).toInt,
                    dateTime.str.substring(8, 10).toInt
                ),
                Time(
                    dateTime.str.substring(11, 13).toInt,
                    dateTime.str.substring(14, 16).toInt
                )
            )
        )

class DateTime(val date: Date, val time: Time) extends DateTimeTrait:
    override def toString(): String = s"${date} ${time}"

    def compare(that: DateTimeTrait): Int = 
        if (that.isInstanceOf[DateTime]) this.compare_datetime(that.asInstanceOf[DateTime])
        else throw new Exception("DateTime.compare(that: DateTimeTrait): Int: that is not a DateTime")

    def compare_datetime(that: DateTime): Int =
        if (this.date.compare(that.date) < 0) -1
        else if (this.date.compare(that.date) > 0) 1
        else if (this.time.compare(that.time) < 0) -1
        else if (this.time.compare(that.time) > 0) 1
        else 0