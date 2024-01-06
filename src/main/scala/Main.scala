import scala.collection.parallel.CollectionConverters._
import scala.collection.parallel.immutable.*

import utils.*

@main def hello: Unit =
  println("Hello world!")
  
  val stations = Station.stations.par
  val dep_station = Station("Lausanne")
  val dep_time = Time("19", "00")

  stations.map(station => Journey(dep_station, station, "2024-01-06", dep_time).head).filter(_.is_reachable(Time("19", "00"), Time("23", "00"))).foreach(println(_))


