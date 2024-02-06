package sbb

import sttp.client4.quick.*
import sttp.client4.Response

import utils._
import SBBClass._
import SBBClass.utils._
import ujson.Value

trait SBBApi:
  val base_url: String = "http://transport.opendata.ch/v1/" 

  protected def makeRequest(endpoint: String, params: Map[String, String]): Response[String] =
    val url = base_url + endpoint
    val queryParams = params.map((k, v) => if (v != "") s"$k=$v" else "").filter(_ != "")
    
    quickRequest.get(uri"$url?$queryParams").send()
  

  protected def getSBBObject(response: Response[String], json_name: String, f: Value => Option[SBBObject]): List[SBBObject] =
    val json = ujson.read(response.body)
    
    try { 
        json(json_name).arr.map(a => f(a).get).toList
    } catch {
        case e: java.util.NoSuchElementException => println("No " + json_name + " found" + s"\n$json"); Nil
    }


object scalaSBB extends SBBApi:
    def get_locations(query: String = "", x: String = "", y: String = "", type_location: TypeLocation = TypeLocation.all): List[Location] = 
        val params = Map(
            "query" -> query,
            "x" -> x,
            "y" -> y,
            "type" -> type_location.toString()
        )

        getSBBObject(makeRequest("locations", params), "stations", Location.decode).asInstanceOf[List[Location]]

    def get_connections(from: String, to: String, via: String = "", date: Date = Date.now(), time: Time = Time.now(), is_arrival_time: Boolean = false, limit: Int = 4, page: Int = 0, direct: Boolean = false, sleeper: Boolean = false, couchette: Boolean = false, bike: Boolean = false, accessibility: TypeAccessibility = TypeAccessibility.independent_boarding): List[Connection] = 
        val params = Map(
            "from" -> from,
            "to" -> to,
            "via" -> via,
            "date" -> date.toString(),
            "time" -> time.toString(),
            "isArrivalTime" -> (if (is_arrival_time) "1" else ""),
            "limit" -> (if (limit != 4) limit.toString() else ""),
            "page" -> (if (page != 0) page.toString() else ""),
            "direct" -> (if (direct) "1" else ""),
            "sleeper" -> (if (sleeper) "1" else ""),
            "couchette" -> (if (couchette) "1" else ""),
            "bike" -> (if (bike) "1" else ""),
            "accessibility" -> (if (accessibility.toString != "") accessibility.toString() else "")
        )

        getSBBObject(makeRequest("connections", params), "connections", Connection.decode).asInstanceOf[List[Connection]]

        //! via est relou à gérer dcp j'ai pas fait

    def get_stationboard(station: String, id: String = "", limit: Int = 4, transportations: TypeTransportation = TypeTransportation.all, datetime: DateTime = DateTime.now(), type_stationboard: TypeStationboard = TypeStationboard.departure): (Location, List[Journey]) =
        val params = Map(
            "station" -> station,
            "id" -> id,
            "limit" -> (if (limit != 4) limit.toString() else ""),
            "transportations" -> (if (transportations.toString != "") transportations.toString() else ""),
            "datetime" -> datetime.toString(),
            "type" -> (if (type_stationboard.toString != "") type_stationboard.toString() else "")
        )

        val response = makeRequest("stationboard", params)

        val location = Location.decode(ujson.read(response.body)("station")).get
        val journeys = getSBBObject(response, "stationboard", Journey.decode).asInstanceOf[List[Journey]]
        
        (location, journeys)

        //! transportations est relou à gérer dcp j'ai pas fait