package br.com.dbserver.lista.agendavacina.utils

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.time.LocalDate


class LocalDateAdapter : TypeAdapter<LocalDate>() {
    @Override
    override fun write(jsonWriter: JsonWriter, localDate :LocalDate) {
        if (localDate == null) {
            jsonWriter.nullValue()
        } else {
            jsonWriter.value(localDate.toString())
        }
    }

    @Override
    override fun read(jsonReader: JsonReader) : LocalDate? {
        return if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull()
            null
        } else {
            LocalDate.parse(jsonReader.nextString())
        }
    }
}