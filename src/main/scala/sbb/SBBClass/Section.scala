package sbb.SBBClass

import ujson.Value

object Section:
    def decode(section: Value): Option[Section] =
        if section.isNull then None
        else Some(
            Section(
                Journey.decode(section("journey")),
                section("walk").strOpt,
                Checkpoint.decode(section("departure")),
                Checkpoint.decode(section("arrival"))
            )
        )

class Section(val journey: Option[Journey], val walk: Option[String], val departure: Option[Checkpoint], val arrival: Option[Checkpoint]):
    override def toString(): String = 
        s"Section(${journey}, ${walk}, ${departure}, ${arrival})" 
