package utils

object Journey:
    def apply(dep_station: Station, arr_station: Station, date: String, time: Time): List[Connection] =
        Sbb.sbb.get_connections(dep_station.get_name(), arr_station.get_name(), date = date, time = time.toString()).as[List[String]].map(Connection(_))


