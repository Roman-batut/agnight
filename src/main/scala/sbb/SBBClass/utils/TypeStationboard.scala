package sbb.SBBClass.utils

object TypeStationboard:
  def withName(name: String): TypeStationboard = name match
    case "arrival" => arrival
    case "departure" => departure

enum TypeStationboard:
  case arrival
  case departure

