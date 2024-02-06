package sbb.SBBClass.utils

object TypeTransportation:
  def withName(name: String): TypeTransportation = name match
          case "train" => train
          case "tram" => tram
          case "ship" => ship
          case "bus" => bus
          case "cableway" => cableway
          case "" => all

enum TypeTransportation:
  case train 
  case tram
  case ship
  case bus
  case cableway
  case all

  override def toString(): String = this match
      case TypeTransportation.train => "train"
      case TypeTransportation.tram => "tram"
      case TypeTransportation.ship => "ship"
      case TypeTransportation.bus => "bus"
      case TypeTransportation.cableway => "cableway"
      case TypeTransportation.all => ""
