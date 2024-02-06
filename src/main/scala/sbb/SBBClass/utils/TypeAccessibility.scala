package sbb.SBBClass.utils

object TypeAccessibility:
    def withName(name: String): TypeAccessibility = name match
            case "independent_boarding" => independent_boarding
            case "assisted_boarding" => assisted_boarding
            case "advanced_notice" => advanced_notice

enum TypeAccessibility:
    case independent_boarding
    case assisted_boarding
    case advanced_notice