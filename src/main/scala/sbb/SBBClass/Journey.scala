package sbb.SBBClass

import ujson.Value

object Journey extends SBBObject:
    def decode(journey: Value): Option[Journey] =
        if journey.isNull then None
        else Some(
            Journey(
                journey("name").strOpt,
                journey("category").strOpt,
                journey("categoryCode").strOpt,
                journey("number").strOpt,
                journey("operator").strOpt,
                journey("to").strOpt,
                if journey("passList").isNull then None else Some(journey("passList").arr.map(Checkpoint.decode).flatten.toSeq),
                journey("capacity1st").numOpt.map(_.toInt),
                journey("capacity2nd").numOpt.map(_.toInt)
            )
        )

class Journey(val name: Option[String], val category: Option[String], val categoryCode: Option[String], val number: Option[String], val operator: Option[String], val to: Option[String], val passList: Option[Seq[Checkpoint]], val capacity1st: Option[Int], val capacity2nd: Option[Int]) extends SBBObject:
    override def toString(): String = 
        s"Journey(${name}, ${category}, ${categoryCode}, ${number}, ${operator}, ${to}, ${passList}, ${capacity1st}, ${capacity2nd})"
