package sbb.SBBClass

import ujson.Value

import sbb.utils._

object Connection extends SBBObject:
    def decode(connection: Value): Option[Connection] =
        if connection.isNull then None
        else Some(
            Connection(
                Checkpoint.decode(connection("from")),
                Checkpoint.decode(connection("to")),
                Time.decode_duration(connection("duration")), //00d00:29:00
                Service.decode(connection("service")),
                if connection("products").isNull then None else Some(connection("products").arr.map(_.str).toList),
                connection("capacity1st").numOpt.map(_.toInt),
                connection("capacity2nd").numOpt.map(_.toInt),
                if connection("sections").isNull then None else Some(connection("sections").arr.map(Section.decode).flatten.toSeq)
            )
        )

class Connection(val from: Option[Checkpoint], val to: Option[Checkpoint], val duration: Option[Time], val service: Option[Service], val products: Option[Seq[String]], val capacity1st: Option[Int], val capacity2nd: Option[Int], val sections: Option[Seq[Section]]) extends SBBObject:
    override def toString(): String = 
        s"Connection(${from}, ${to}, ${duration}, ${service}, ${products}, ${capacity1st}, ${capacity2nd}, ${sections})"

