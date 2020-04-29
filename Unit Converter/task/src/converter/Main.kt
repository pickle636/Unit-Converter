package converter

import java.util.Scanner

enum class Units(val pluralName: String, val abbrev: String, val singleName: String, val celFar2c: String, val value: Double, val type: String, val far: String) {
    METERS("meters", "m", "meter", "", 1.0, "length", ""),
    KILOMETERS("kilometers", "km", "kilometer", "", 1000.0, "length", ""),
    CENTIMETERS("centimeters", "cm", "centimeter", "", 0.01, "length", ""),
    MILLIMETERS("millimeters", "mm", "millimeter", "", 0.001, "length", ""),
    MILES("miles", "mi", "mile", "", 1609.35, "length", ""),
    YARDS("yards", "yd", "yard", "", 0.9144, "length", ""),
    FEET("feet", "ft", "foot", "", 0.3048, "length", ""),
    INCHES("inches", "in", "inch", "", 0.0254, "length", ""),
    GRAM("grams", "g", "gram", "", 1.0, "weight", ""),
    KILOGRAMS("kilograms", "kg", "kilogram", "", 1000.0, "weight", ""),
    MILLIGRAMS("milligrams", "mg", "milligram", "", 0.001, "weight", ""),
    POUND("pounds", "lb", "pound", "", 453.592, "weight", ""),
    OUNCES("ounces", "oz", "ounce", "", 28.3495, "weight", ""),
    KELVIN("Kelvins", "k", "Kelvin", "", 0.0, "temp", ""),
    CELSIUS("degrees Celsius", "c", "degree Celsius", "dc", 0.0, "temp", "Celsius"),
    FAHRENHEIT("degrees Fahrenheit", "f", "degree Fahrenheit", "df", 0.0, "temp", "fahrenheit"),
    UNDEFINED("???", "???", "???", "???", 0.0, "???", "???");


    companion object {
        fun findName(name: String): Units {
            for (n in values()) {
                if (n.pluralName.toLowerCase() == name || n.abbrev.toLowerCase() == name || n.singleName.toLowerCase() == name || n.celFar2c.toLowerCase() == name || n.far.toLowerCase() == name) {
                    return n
                }
            }
            return UNDEFINED
        }
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    var num1 = 0.0
    while (scanner.hasNext()) {
        print("Enter what you want to convert (or exit): ")
        var num = scanner.next()
        if (num == "exit"){
            break
        } else if (num == ""){
            println("Parse error")
            return
        } else if (num is String) {
            num1 = num.toDouble()
        }


        var unit = scanner.next().toLowerCase()
        if (unit == "degrees" || unit == "degree") {
            var u = scanner.next().toLowerCase()
            unit = "$unit $u"
        }

        var to = scanner.next()

        var unit2 = scanner.next().toLowerCase()
        if (unit2 == "degrees" || unit2 == "degree") {
            var u = scanner.next().toLowerCase()
            unit2 = "$unit2 $u"
        }

        var firstUnitInMeters = Units.findName(unit)
        var secondUnitInMeters = Units.findName(unit2)

        if (firstUnitInMeters.type != secondUnitInMeters.type || firstUnitInMeters.type == "???" || secondUnitInMeters.type == "???") {
            println("Conversion from ${firstUnitInMeters.pluralName} to ${secondUnitInMeters.pluralName} is impossible")
        } else if (firstUnitInMeters.type == secondUnitInMeters.type && firstUnitInMeters.type != "temp") {
            if (num1 < 0 && firstUnitInMeters.type == "length") {
                println("Length shouldn't be negative")
            } else if (num1 < 0 && firstUnitInMeters.type == "weight") {
                println("Weight shouldn't be negative")
            } else {
                var allInMeters = (num1 * firstUnitInMeters.value) / secondUnitInMeters.value
                println("$num1 ${if (num1 in 0.0..0.9 || num1 > 1.0) firstUnitInMeters.pluralName else firstUnitInMeters.singleName} is $allInMeters ${if (allInMeters in 0.0..0.9 || allInMeters > 1.0) secondUnitInMeters.pluralName else secondUnitInMeters.singleName}")
            }
        } else {
            var res = 0.0
            when {
                firstUnitInMeters.singleName == "Kelvin" && secondUnitInMeters.singleName == "degree Celsius" -> {
                    res = num1 - 273.15
                }
                firstUnitInMeters.singleName == "degree Celsius" && secondUnitInMeters.singleName == "Kelvin" -> {
                    res = num1 + 273.15
                }
                firstUnitInMeters.singleName == "degree Celsius" && secondUnitInMeters.singleName == "degree Fahrenheit" -> {
                    res = num1 * 9 / 5 + 32
                }
                firstUnitInMeters.singleName == "degree Fahrenheit" && secondUnitInMeters.singleName == "degree Celsius" -> {
                    res = (num1 - 32) * 5 / 9
                }
                firstUnitInMeters.singleName == "Kelvin" && secondUnitInMeters.singleName == "degree Fahrenheit" -> {
                    res = num1 * 9 / 5 - 459.67
                }
                firstUnitInMeters.singleName == "degree Fahrenheit" && secondUnitInMeters.singleName == "Kelvin" -> {
                    res = (num1 + 459.67) * 5 / 9
                }
                else -> res = num1
            }
            println("$num1 ${if (num1 in 0.0..0.9 || num1 > 1.0 || num1 < -1) firstUnitInMeters.pluralName else firstUnitInMeters.singleName} is $res ${if (res in 0.0..0.9 || res > 1.0 || res < -1) secondUnitInMeters.pluralName else secondUnitInMeters.singleName}")
        }

    }
}