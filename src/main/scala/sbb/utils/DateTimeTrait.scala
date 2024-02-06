package sbb.utils

trait DateTimeTrait:
    def compare(that: DateTimeTrait): Int 
    
    def isBefore(that: DateTimeTrait): Boolean =
        if (this.compare(that) < 0) true
        else false

    def isAfter(that: DateTimeTrait): Boolean =
        if (this.compare(that) > 0) true
        else false

    def isEqual(that: DateTimeTrait): Boolean =
        if (this.compare(that) == 0) true
        else false

    def isBetweenStrict(start: DateTimeTrait, end: DateTimeTrait): Boolean =
        if (this.isAfter(start) && this.isBefore(end)) true
        else false

    def isBetweenEqual(start: DateTimeTrait, end: DateTimeTrait): Boolean =
        if (this.isAfter(start) && this.isBefore(end) || this.isEqual(start) || this.isEqual(end)) true
        else false
