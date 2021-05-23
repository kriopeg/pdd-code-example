package ru.kriopeg.example.data.db

object Contracts {

    const val DB_NAME = "pdd.db"

    object Tables {

        object Question {
            const val TABLE_NAME = "questions"
            const val FIELD_ID = "id"
            const val FIELD_TICKET = "ticket"
            const val FIELD_NUMBER = "number"
            const val FIELD_BLOCK = "block"
            const val FIELD_TEXT = "text"
            const val FIELD_HINT = "hint"
            const val FIELD_IMAGE_PATH = "image_path"
            const val FIELD_RIGHT_OPTION = "right_option"
            const val FIELD_CATEGORY = "category"
        }

        object QuestionStats {
            const val TABLE_NAME = "question_stats"
            const val FIELD_ID = "id"
            const val FIELD_QUESTION_ID = "question_id"
            const val FIELD_ANSWERS_COUNT = "answers_count"
            const val FIELD_RIGHT_COUNT = "right_count"
            const val FIELD_RIGHT_IN_A_ROW = "right_in_a_row"
            const val FIELD_SCENARIO = "scenario"
        }

        object QuestionWeight {
            const val TABLE_NAME = "question_weight"
            const val FIELD_ID = "id"
            const val FIELD_WEIGHT = "weight"
            const val FIELD_RIGHT_IN_A_ROW = "right_in_a_row"
        }
    }
}