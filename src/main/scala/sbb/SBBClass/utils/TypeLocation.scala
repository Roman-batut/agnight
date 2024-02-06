package sbb.SBBClass.utils

object TypeLocation:
    def withName(name: String): TypeLocation = name match
            case "station" => station
            case "poi" => poi
            case "address" => address
            case "refine" => refine
            case "all" => all

enum TypeLocation:
    case station
    case poi
    case address
    case refine
    case all
