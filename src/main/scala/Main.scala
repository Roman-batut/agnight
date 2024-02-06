import scala.collection.parallel.CollectionConverters._
import scala.collection.parallel.immutable.*

import util._
import sbb.SBBClass.utils._
import sbb.SBBClass._
import sbb.utils._
import sbb.scalaSBB

@main def main: Unit =
  
  val stations = Station.stations.par

  val dep_station = scalaSBB.get_locations("Lausanne", type_location = TypeLocation.station).head
  val dep_time = Time(19, 0)
  val arr_time_max = DateTime(Date(2024, 1,7), Time(23, 0))

  println(dep_station.name.get)

  var nb = 0

  val connections = stations.map(s => 
    scalaSBB.get_connections(dep_station.name.get, {nb+=1; println(s.get_name() + s":$nb/751"); s.get_name()}, time = dep_time).headOption)

  println("filtering")

  val connections_filtered = connections.seq.filter(con => {println((con.isEmpty || con.get.to.get.arrival.get.compare(arr_time_max) <= 0));(con.get.to.get.arrival.get.compare(arr_time_max) <= 0)}).foreach(println(_))

  println("done")
