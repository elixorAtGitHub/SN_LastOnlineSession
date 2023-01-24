const val ERROR_PERIOD = "(!!!ERROR - Период времени задан ошибочно!!!)"
const val ERROR_END_OF_MINUTES = "(!!!ERROR - Ошибка при вычислении окончания у слова МИНУТ!!!)"
const val ERROR_END_OF_HOURS = "(!!!ERROR - Ошибка при вычислении окончания у слова ЧАС!!!)"

fun main() {
    val timeDifference = 80000 //задаем период отсутствия активности пользователя в секундах
    println(agoToText(timeDifference))
}


fun agoToText(timeDifference : Int): String = when (timeDifference) {
    in 0..60 -> "только что"
    in 60..60*60 -> (timeDifference/60).toString() + " минут" + endOfMinutes(timeDifference) + " назад"
    in 60*60+1..24*60*60 -> (timeDifference/(60*60)).toString() + " час" + endOfHours(timeDifference) + " назад"
    in 24*60*60+1..2*24*60*60 -> "вчера"
    in 2*24*60*60+1..3*24*60*60 -> "позавчера"
    in 3*24*60*60+1..Int.MAX_VALUE -> "давно"
    else -> ERROR_PERIOD
}

fun endOfMinutes(timeDifference: Int): String {
    if (timeDifference/60 in 11..14) return ""
    else if (timeDifference/60%10 == 1) return "у"
    else if (timeDifference/60%10 in 2..4) return "ы"
    else if ((timeDifference/60%10 == 0) || (timeDifference/60%10 >= 5)) return ""
    else return ERROR_END_OF_MINUTES
}

fun endOfHours(timeDifference: Int): String {
    val hours = timeDifference/(60*60)
    if (hours in 5..20) return "ов"
    else if (hours%10 == 1) return ""
    else if (hours%10 in 2..4) return "а"
    else return ERROR_END_OF_HOURS
}