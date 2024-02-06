package sbb.SBBClass

import ujson.Value

import sbb.utils._

object Checkpoint:
    def decode(checkpoint: Value): Option[Checkpoint] =
        if checkpoint.isNull then None
        else Some(
            Checkpoint(
                Location.decode(checkpoint("station")),
                DateTime.decode(checkpoint("arrival")),
                DateTime.decode(checkpoint("departure")),
                checkpoint("delay").numOpt.map(_.toInt),
                checkpoint("platform").numOpt.map(_.toInt),
                Prognosis.decode(checkpoint("prognosis"))
            )
        )

class Checkpoint(val station: Option[Location], val arrival: Option[DateTime], val departure: Option[DateTime], val delay: Option[Int], val platform: Option[Int], val prognosis: Option[Prognosis]):
    override def toString(): String = 
        s"Checkpoint(${station}, ${arrival}, ${departure}, ${delay}, ${platform}, ${prognosis})"
