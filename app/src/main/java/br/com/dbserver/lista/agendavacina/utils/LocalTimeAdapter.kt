package br.com.dbserver.lista.agendavacina.utils

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.time.LocalTime


class LocalTimeAdapter : TypeAdapter<LocalTime>() {
    @Override
    override fun write(jsonWriter: JsonWriter, localTime :LocalTime) {
        if (localTime == null) {
            jsonWriter.nullValue()
        } else {
            jsonWriter.value(localTime.toString())
        }
    }

    @Override
    override fun read(jsonReader: JsonReader) : LocalTime? {
        return if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull()
            null
        } else {
            LocalTime.parse(jsonReader.nextString())
        }
    }
}