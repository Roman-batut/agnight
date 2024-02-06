package sbb.SBBClass

import ujson.Value

object Service:
    def decode(service: Value): Option[Service] =
        if service.isNull then None
        else Some(
            Service(
                service("regular").strOpt,
                service("irregular").strOpt
            )
        )

class Service(val regular: Option[String], val irregular: Option[String]):
    override def toString(): String = 
        s"Service(${regular}, ${irregular})"
