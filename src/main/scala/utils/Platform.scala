package utils

case class Platform(plat: Option[String]):
    private val plat_nbr: Option[Int] = plat match
        case Some(value) => Some(value.toInt)
        case None => None
    
    override def toString(): String = plat_nbr match
        case Some(value) => "Plat. " + value
        case None => ""
    
