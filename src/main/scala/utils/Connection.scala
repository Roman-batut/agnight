package utils

class Connection(connection: String):
    //Lausanne (19:19, Plat. 1) -> Aadorf (22:28, Plat. 1) | 3h 9min
    //Lausanne (19:19) -> Aadorf (22:28) | 3h 9min

    private val dep_station : Station = Station(connection.split(" -> ")(0).split(" (")(0))
    private val arr_station : Station = Station(connection.split(" -> ")(1).split(" (")(0))
    private val dep_time : Time = Time(connection.split(" -> ")(0).split(" (")(1).split(":")(0), connection.split(" -> ")(0).split(" (")(1).split(":")(1).slice(0, 2))
    private val arr_time : Time = Time(connection.split(" -> ")(1).split(" (")(1).split(":")(0), connection.split(" -> ")(1).split(" (")(1).split(":")(1).slice(0, 2))
    private val dep_platform : Platform = try { Platform(Some(connection.split(" -> ")(0).split(" (")(1).split(", Plat. ")(1).split(")")(0)))} catch { case e: Exception => Platform(None) }
    private val arr_platform : Platform = try { Platform(Some(connection.split(" -> ")(1).split(" (")(1).split(", Plat. ")(1).split(")")(0)))} catch { case e: Exception => Platform(None) }
    private val duration : Time = dep_time.duration(arr_time)

    def getDepStation(): Station = dep_station
    def getArrStation(): Station = arr_station
    def getDepTime(): Time = dep_time
    def getArrTime(): Time = arr_time
    def getDepPlatform(): Platform = dep_platform
    def getArrPlatform(): Platform = arr_platform
    def getDuration(): Time = duration

    def is_reachable(start: Time, end: Time): Boolean = dep_time.isBetweenEqual(start, end) && arr_time.isBetweenEqual(start, end)
