package sbb.SBBClass

import ujson.Value

import sbb.SBBClass.utils._

object Location extends SBBObject:
    def decode(station: Value): Option[Location] =
        if station.isNull then None
        else Some(
            Location(
                    station("id").strOpt,
                    station("name").strOpt,
                    station("score").strOpt, 
                    Coordinates.decode(station("coordinate")), 
                    station("distance").strOpt,
                )
        )

class Location(val id: Option[String], val name: Option[String], val score: Option[String], val coordinates: Option[Coordinates], val distance: Option[String], val type_location: TypeLocation = TypeLocation.refine) extends SBBObject:
    override def toString(): String = 
        s"Location(${id.getOrElse("None")}, ${name.getOrElse("None")}, ${score.getOrElse("None")}, ${coordinates}, ${distance.getOrElse("None")}, ${type_location.toString()})"





