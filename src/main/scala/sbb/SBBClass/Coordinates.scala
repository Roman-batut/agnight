package sbb.SBBClass

import ujson.Value

object Coordinates:
    def decode(coordinates: Value): Option[Coordinates] =
        if coordinates.isNull then None
        else Some(
            Coordinates(
                coordinates("type").strOpt,
                coordinates("x").numOpt,
                coordinates("y").numOpt
            )
        )

class Coordinates(val type_coordinates: Option[String], val x: Option[Double], val y: Option[Double]):
    override def toString(): String = 
        s"Coordinates(${type_coordinates}, ${x}, ${y})"
