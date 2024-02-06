package sbb.SBBClass

import ujson.Value

import sbb.utils._

object Prognosis:
    def decode(prognosis: Value): Option[Prognosis] =
        if prognosis.isNull then None
        else Some(
            Prognosis(
                prognosis("platform").numOpt.map(_.toInt),
                DateTime.decode(prognosis("arrival")),
                DateTime.decode(prognosis("departure")),
                prognosis("capacity1st").numOpt.map(_.toInt),
                prognosis("capacity2nd").numOpt.map(_.toInt)
            )
        )
            
class Prognosis(val platform: Option[Int], val arrival: Option[DateTime], val departure: Option[DateTime], val capacity1st: Option[Int], val capacity2nd: Option[Int]):
    override def toString(): String = 
        s"Prognosis(${platform}, ${arrival}, ${departure}, ${capacity1st}, ${capacity2nd})"
