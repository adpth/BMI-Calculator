package com.tharun.bmicalculator

object Utils {

    fun checkAdult(age: Int, result: Float): String {
        val category:String = when (age) {
            in 2..19 -> {
                getAdultCategory(result)
            }
            else -> {
                getChildCategory(result)
            }
        }
        return category
    }

    private fun getAdultCategory(result: Float): String {
        val category: String = if (result < 15) {
            "Severe Thinness"
        } else if (result in 15.0..16.0) {
            "Moderate Thinness"
        } else if (result > 16 && result <= 18.5) {
            "Mild Thinness"
        } else if (result > 18.5 && result <= 25) {
            "Normal"
        } else if (result > 25 && result <= 30) {
            "Overweight"
        } else if (result > 30 && result <= 35) {
            "Obese Class I"
        } else if (result > 35 && result <= 40) {
            "Obese Class II"
        } else {
            "Obese Class III"
        }
        return category
    }

    private fun getChildCategory(result: Float): String {
        val category: String = when {
            result < 15 -> {
                "very severely underweight"
            }
            result in 15.0..16.0 -> {
                "severely underweight"
            }
            result > 16 && result <= 18.5 -> {
                "underweight"
            }
            result > 18.5 && result <= 25 -> {
                "normal (healthy weight)"
            }
            result > 25 && result <= 30 -> {
                "overweight"
            }
            result > 30 && result <= 35 -> {
                "moderately obese"
            }
            result > 35 && result <= 40 -> {
                "severely obese"
            }
            else -> {
                "very severely obese"
            }
        }
        return category
    }

    fun getSuggestions(result: Float): String {
        val suggestion: String = when {
            result < 18.5 -> {
                "A BMI of under 18.5 indicates that a person has insufficient weight, so they may need to put on some weight. They should ask a doctor or dietitian for advice."
            }
            result in 18.5..24.9 -> {
                "A BMI of 18.5–24.9 indicates that a person has a healthy weight for their height. By maintaining a healthy weight, they can lower their risk of developing serious health problems."
            }
            result < 25 && result >=29.9 -> {
                "A BMI of 25–29.9 indicates that a person is slightly overweight. A doctor may advise them to lose some weight for health reasons. They should talk with a doctor or dietitian for advice."
            }
            else -> {
                "A BMI of over 30 indicates that a person has obesity. Their health may be at risk if they do not lose weight. They should talk with a doctor or dietitian for advice."
            }
        }
        return suggestion
    }
}