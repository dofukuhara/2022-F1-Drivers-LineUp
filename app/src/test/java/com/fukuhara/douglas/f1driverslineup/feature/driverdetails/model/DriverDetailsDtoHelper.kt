package com.fukuhara.douglas.f1driverslineup.feature.driverdetails.model

import com.fukuhara.douglas.f1driverslineup.feature.driverdetails.repository.dto.DriverDetailsDto
import com.google.gson.Gson

object DriverDetailsDtoHelper {
    private fun jsonToDto(json: String): DriverDetailsDto = Gson().fromJson(json, DriverDetailsDto::class.java)

    fun generateCompleteSingleRaceDto(): DriverDetailsDto = jsonToDto(COMPLETE_SINGLE_RACE)
    fun generateCompleteThreeRacesDto(): DriverDetailsDto = jsonToDto(COMPLETE_THREE_RACES)
    fun generateTwoCompleteOneMissingLapsDto(): DriverDetailsDto = jsonToDto(TWO_COMPLETE_ONE_MISSING_LAPS)
    fun generateIncompleteSingleMissingSeason(): DriverDetailsDto = jsonToDto(INCOMPLETE_SINGLE_MISSING_SEASON)
    fun generateIncompleteSingleMissingRound(): DriverDetailsDto = jsonToDto(INCOMPLETE_SINGLE_MISSING_ROUND)
    fun generateIncompleteSingleMissingPosition(): DriverDetailsDto = jsonToDto(INCOMPLETE_SINGLE_MISSING_POSITION)
    fun generateIncompleteSingleMissingPoints(): DriverDetailsDto = jsonToDto(INCOMPLETE_SINGLE_MISSING_POINTS)
    fun generateIncompleteSingleMissingGrid(): DriverDetailsDto = jsonToDto(INCOMPLETE_SINGLE_MISSING_GRID)
    fun generateIncompleteSingleMissingLaps(): DriverDetailsDto = jsonToDto(INCOMPLETE_SINGLE_MISSING_LAPS)
    fun generateIncompleteSingleMissingStatus(): DriverDetailsDto = jsonToDto(INCOMPLETE_SINGLE_MISSING_STATUS)
    fun generateIncompleteSingleMissingFastestLap(): DriverDetailsDto = jsonToDto(INCOMPLETE_SINGLE_MISSING_FASTESTLAP)
    fun generateIncompleteSingleMissingFastestLapRank(): DriverDetailsDto = jsonToDto(INCOMPLETE_SINGLE_MISSING_FASTESTLAP_RANK)
}

private const val COMPLETE_SINGLE_RACE = "{\n" +
    "  \"driverId\": \"albon\",\n" +
    "  \"Races\": [\n" +
    "    {\n" +
    "      \"season\": \"2022\",\n" +
    "      \"round\": \"22\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix\",\n" +
    "      \"raceName\": \"Abu Dhabi Grand Prix\",\n" +
    "      \"Circuit\": {\n" +
    "        \"circuitId\": \"yas_marina\",\n" +
    "        \"url\": \"http://en.wikipedia.org/wiki/Yas_Marina_Circuit\",\n" +
    "        \"circuitName\": \"Yas Marina Circuit\",\n" +
    "        \"Location\": {\n" +
    "          \"lat\": \"24.4672\",\n" +
    "          \"long\": \"54.6031\",\n" +
    "          \"locality\": \"Abu Dhabi\",\n" +
    "          \"country\": \"UAE\"\n" +
    "        }\n" +
    "      },\n" +
    "      \"date\": \"2022-11-20\",\n" +
    "      \"time\": \"13:00:00Z\",\n" +
    "      \"Results\": [\n" +
    "        {\n" +
    "          \"number\": \"23\",\n" +
    "          \"position\": \"13\",\n" +
    "          \"positionText\": \"13\",\n" +
    "          \"points\": \"0\",\n" +
    "          \"Driver\": {\n" +
    "            \"driverId\": \"albon\",\n" +
    "            \"permanentNumber\": \"23\",\n" +
    "            \"code\": \"ALB\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "            \"givenName\": \"Alexander\",\n" +
    "            \"familyName\": \"Albon\",\n" +
    "            \"dateOfBirth\": \"1996-03-23\",\n" +
    "            \"nationality\": \"Thai\"\n" +
    "          },\n" +
    "          \"Constructor\": {\n" +
    "            \"constructorId\": \"williams\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Williams_Grand_Prix_Engineering\",\n" +
    "            \"name\": \"Williams\",\n" +
    "            \"nationality\": \"British\"\n" +
    "          },\n" +
    "          \"grid\": \"19\",\n" +
    "          \"laps\": \"57\",\n" +
    "          \"status\": \"+1 Lap\",\n" +
    "          \"FastestLap\": {\n" +
    "            \"rank\": \"13\",\n" +
    "            \"lap\": \"40\",\n" +
    "            \"Time\": {\n" +
    "              \"time\": \"1:29.939\"\n" +
    "            },\n" +
    "            \"AverageSpeed\": {\n" +
    "              \"units\": \"kph\",\n" +
    "              \"speed\": \"211.383\"\n" +
    "            }\n" +
    "          }\n" +
    "        }\n" +
    "      ]\n" +
    "    }\n" +
    "  ]\n" +
    "}"

private const val COMPLETE_THREE_RACES = "{\n" +
    "  \"driverId\": \"albon\",\n" +
    "  \"Races\": [\n" +
    "    {\n" +
    "      \"season\": \"2022\",\n" +
    "      \"round\": \"20\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/2022_Mexican_Grand_Prix\",\n" +
    "      \"raceName\": \"Mexico City Grand Prix\",\n" +
    "      \"Circuit\": {\n" +
    "        \"circuitId\": \"rodriguez\",\n" +
    "        \"url\": \"http://en.wikipedia.org/wiki/Aut%C3%B3dromo_Hermanos_Rodr%C3%ADguez\",\n" +
    "        \"circuitName\": \"Autódromo Hermanos Rodríguez\",\n" +
    "        \"Location\": {\n" +
    "          \"lat\": \"19.4042\",\n" +
    "          \"long\": \"-99.0907\",\n" +
    "          \"locality\": \"Mexico City\",\n" +
    "          \"country\": \"Mexico\"\n" +
    "        }\n" +
    "      },\n" +
    "      \"date\": \"2022-10-30\",\n" +
    "      \"time\": \"20:00:00Z\",\n" +
    "      \"Results\": [\n" +
    "        {\n" +
    "          \"number\": \"23\",\n" +
    "          \"position\": \"12\",\n" +
    "          \"positionText\": \"12\",\n" +
    "          \"points\": \"0\",\n" +
    "          \"Driver\": {\n" +
    "            \"driverId\": \"albon\",\n" +
    "            \"permanentNumber\": \"23\",\n" +
    "            \"code\": \"ALB\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "            \"givenName\": \"Alexander\",\n" +
    "            \"familyName\": \"Albon\",\n" +
    "            \"dateOfBirth\": \"1996-03-23\",\n" +
    "            \"nationality\": \"Thai\"\n" +
    "          },\n" +
    "          \"Constructor\": {\n" +
    "            \"constructorId\": \"williams\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Williams_Grand_Prix_Engineering\",\n" +
    "            \"name\": \"Williams\",\n" +
    "            \"nationality\": \"British\"\n" +
    "          },\n" +
    "          \"grid\": \"17\",\n" +
    "          \"laps\": \"70\",\n" +
    "          \"status\": \"+1 Lap\",\n" +
    "          \"FastestLap\": {\n" +
    "            \"rank\": \"12\",\n" +
    "            \"lap\": \"48\",\n" +
    "            \"Time\": {\n" +
    "              \"time\": \"1:22.914\"\n" +
    "            },\n" +
    "            \"AverageSpeed\": {\n" +
    "              \"units\": \"kph\",\n" +
    "              \"speed\": \"186.873\"\n" +
    "            }\n" +
    "          }\n" +
    "        }\n" +
    "      ]\n" +
    "    },\n" +
    "    {\n" +
    "      \"season\": \"2022\",\n" +
    "      \"round\": \"21\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/2022_Brazilian_Grand_Prix\",\n" +
    "      \"raceName\": \"Brazilian Grand Prix\",\n" +
    "      \"Circuit\": {\n" +
    "        \"circuitId\": \"interlagos\",\n" +
    "        \"url\": \"http://en.wikipedia.org/wiki/Aut%C3%B3dromo_Jos%C3%A9_Carlos_Pace\",\n" +
    "        \"circuitName\": \"Autódromo José Carlos Pace\",\n" +
    "        \"Location\": {\n" +
    "          \"lat\": \"-23.7036\",\n" +
    "          \"long\": \"-46.6997\",\n" +
    "          \"locality\": \"São Paulo\",\n" +
    "          \"country\": \"Brazil\"\n" +
    "        }\n" +
    "      },\n" +
    "      \"date\": \"2022-11-13\",\n" +
    "      \"time\": \"18:00:00Z\",\n" +
    "      \"Results\": [\n" +
    "        {\n" +
    "          \"number\": \"23\",\n" +
    "          \"position\": \"15\",\n" +
    "          \"positionText\": \"15\",\n" +
    "          \"points\": \"0\",\n" +
    "          \"Driver\": {\n" +
    "            \"driverId\": \"albon\",\n" +
    "            \"permanentNumber\": \"23\",\n" +
    "            \"code\": \"ALB\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "            \"givenName\": \"Alexander\",\n" +
    "            \"familyName\": \"Albon\",\n" +
    "            \"dateOfBirth\": \"1996-03-23\",\n" +
    "            \"nationality\": \"Thai\"\n" +
    "          },\n" +
    "          \"Constructor\": {\n" +
    "            \"constructorId\": \"williams\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Williams_Grand_Prix_Engineering\",\n" +
    "            \"name\": \"Williams\",\n" +
    "            \"nationality\": \"British\"\n" +
    "          },\n" +
    "          \"grid\": \"19\",\n" +
    "          \"laps\": \"71\",\n" +
    "          \"status\": \"Finished\",\n" +
    "          \"Time\": {\n" +
    "            \"millis\": \"5950060\",\n" +
    "            \"time\": \"+36.016\"\n" +
    "          },\n" +
    "          \"FastestLap\": {\n" +
    "            \"rank\": \"16\",\n" +
    "            \"lap\": \"49\",\n" +
    "            \"Time\": {\n" +
    "              \"time\": \"1:15.613\"\n" +
    "            },\n" +
    "            \"AverageSpeed\": {\n" +
    "              \"units\": \"kph\",\n" +
    "              \"speed\": \"205.155\"\n" +
    "            }\n" +
    "          }\n" +
    "        }\n" +
    "      ]\n" +
    "    },\n" +
    "    {\n" +
    "      \"season\": \"2022\",\n" +
    "      \"round\": \"22\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix\",\n" +
    "      \"raceName\": \"Abu Dhabi Grand Prix\",\n" +
    "      \"Circuit\": {\n" +
    "        \"circuitId\": \"yas_marina\",\n" +
    "        \"url\": \"http://en.wikipedia.org/wiki/Yas_Marina_Circuit\",\n" +
    "        \"circuitName\": \"Yas Marina Circuit\",\n" +
    "        \"Location\": {\n" +
    "          \"lat\": \"24.4672\",\n" +
    "          \"long\": \"54.6031\",\n" +
    "          \"locality\": \"Abu Dhabi\",\n" +
    "          \"country\": \"UAE\"\n" +
    "        }\n" +
    "      },\n" +
    "      \"date\": \"2022-11-20\",\n" +
    "      \"time\": \"13:00:00Z\",\n" +
    "      \"Results\": [\n" +
    "        {\n" +
    "          \"number\": \"23\",\n" +
    "          \"position\": \"13\",\n" +
    "          \"positionText\": \"13\",\n" +
    "          \"points\": \"0\",\n" +
    "          \"Driver\": {\n" +
    "            \"driverId\": \"albon\",\n" +
    "            \"permanentNumber\": \"23\",\n" +
    "            \"code\": \"ALB\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "            \"givenName\": \"Alexander\",\n" +
    "            \"familyName\": \"Albon\",\n" +
    "            \"dateOfBirth\": \"1996-03-23\",\n" +
    "            \"nationality\": \"Thai\"\n" +
    "          },\n" +
    "          \"Constructor\": {\n" +
    "            \"constructorId\": \"williams\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Williams_Grand_Prix_Engineering\",\n" +
    "            \"name\": \"Williams\",\n" +
    "            \"nationality\": \"British\"\n" +
    "          },\n" +
    "          \"grid\": \"19\",\n" +
    "          \"laps\": \"57\",\n" +
    "          \"status\": \"+1 Lap\",\n" +
    "          \"FastestLap\": {\n" +
    "            \"rank\": \"13\",\n" +
    "            \"lap\": \"40\",\n" +
    "            \"Time\": {\n" +
    "              \"time\": \"1:29.939\"\n" +
    "            },\n" +
    "            \"AverageSpeed\": {\n" +
    "              \"units\": \"kph\",\n" +
    "              \"speed\": \"211.383\"\n" +
    "            }\n" +
    "          }\n" +
    "        }\n" +
    "      ]\n" +
    "    }\n" +
    "  ]\n" +
    "}"

private const val TWO_COMPLETE_ONE_MISSING_LAPS = "{\n" +
    "  \"driverId\": \"albon\",\n" +
    "  \"Races\": [\n" +
    "    {\n" +
    "      \"season\": \"2022\",\n" +
    "      \"round\": \"20\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/2022_Mexican_Grand_Prix\",\n" +
    "      \"raceName\": \"Mexico City Grand Prix\",\n" +
    "      \"Circuit\": {\n" +
    "        \"circuitId\": \"rodriguez\",\n" +
    "        \"url\": \"http://en.wikipedia.org/wiki/Aut%C3%B3dromo_Hermanos_Rodr%C3%ADguez\",\n" +
    "        \"circuitName\": \"Autódromo Hermanos Rodríguez\",\n" +
    "        \"Location\": {\n" +
    "          \"lat\": \"19.4042\",\n" +
    "          \"long\": \"-99.0907\",\n" +
    "          \"locality\": \"Mexico City\",\n" +
    "          \"country\": \"Mexico\"\n" +
    "        }\n" +
    "      },\n" +
    "      \"date\": \"2022-10-30\",\n" +
    "      \"time\": \"20:00:00Z\",\n" +
    "      \"Results\": [\n" +
    "        {\n" +
    "          \"number\": \"23\",\n" +
    "          \"position\": \"12\",\n" +
    "          \"positionText\": \"12\",\n" +
    "          \"points\": \"0\",\n" +
    "          \"Driver\": {\n" +
    "            \"driverId\": \"albon\",\n" +
    "            \"permanentNumber\": \"23\",\n" +
    "            \"code\": \"ALB\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "            \"givenName\": \"Alexander\",\n" +
    "            \"familyName\": \"Albon\",\n" +
    "            \"dateOfBirth\": \"1996-03-23\",\n" +
    "            \"nationality\": \"Thai\"\n" +
    "          },\n" +
    "          \"Constructor\": {\n" +
    "            \"constructorId\": \"williams\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Williams_Grand_Prix_Engineering\",\n" +
    "            \"name\": \"Williams\",\n" +
    "            \"nationality\": \"British\"\n" +
    "          },\n" +
    "          \"grid\": \"17\",\n" +
    "          \"laps\": \"70\",\n" +
    "          \"status\": \"+1 Lap\",\n" +
    "          \"FastestLap\": {\n" +
    "            \"rank\": \"12\",\n" +
    "            \"lap\": \"48\",\n" +
    "            \"Time\": {\n" +
    "              \"time\": \"1:22.914\"\n" +
    "            },\n" +
    "            \"AverageSpeed\": {\n" +
    "              \"units\": \"kph\",\n" +
    "              \"speed\": \"186.873\"\n" +
    "            }\n" +
    "          }\n" +
    "        }\n" +
    "      ]\n" +
    "    },\n" +
    "    {\n" +
    "      \"season\": \"2022\",\n" +
    "      \"round\": \"21\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/2022_Brazilian_Grand_Prix\",\n" +
    "      \"raceName\": \"Brazilian Grand Prix\",\n" +
    "      \"Circuit\": {\n" +
    "        \"circuitId\": \"interlagos\",\n" +
    "        \"url\": \"http://en.wikipedia.org/wiki/Aut%C3%B3dromo_Jos%C3%A9_Carlos_Pace\",\n" +
    "        \"circuitName\": \"Autódromo José Carlos Pace\",\n" +
    "        \"Location\": {\n" +
    "          \"lat\": \"-23.7036\",\n" +
    "          \"long\": \"-46.6997\",\n" +
    "          \"locality\": \"São Paulo\",\n" +
    "          \"country\": \"Brazil\"\n" +
    "        }\n" +
    "      },\n" +
    "      \"date\": \"2022-11-13\",\n" +
    "      \"time\": \"18:00:00Z\",\n" +
    "      \"Results\": [\n" +
    "        {\n" +
    "          \"number\": \"23\",\n" +
    "          \"position\": \"15\",\n" +
    "          \"positionText\": \"15\",\n" +
    "          \"points\": \"0\",\n" +
    "          \"Driver\": {\n" +
    "            \"driverId\": \"albon\",\n" +
    "            \"permanentNumber\": \"23\",\n" +
    "            \"code\": \"ALB\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "            \"givenName\": \"Alexander\",\n" +
    "            \"familyName\": \"Albon\",\n" +
    "            \"dateOfBirth\": \"1996-03-23\",\n" +
    "            \"nationality\": \"Thai\"\n" +
    "          },\n" +
    "          \"Constructor\": {\n" +
    "            \"constructorId\": \"williams\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Williams_Grand_Prix_Engineering\",\n" +
    "            \"name\": \"Williams\",\n" +
    "            \"nationality\": \"British\"\n" +
    "          },\n" +
    "          \"grid\": \"19\",\n" +
    "          \"status\": \"Finished\",\n" +
    "          \"Time\": {\n" +
    "            \"millis\": \"5950060\",\n" +
    "            \"time\": \"+36.016\"\n" +
    "          },\n" +
    "          \"FastestLap\": {\n" +
    "            \"rank\": \"16\",\n" +
    "            \"lap\": \"49\",\n" +
    "            \"Time\": {\n" +
    "              \"time\": \"1:15.613\"\n" +
    "            },\n" +
    "            \"AverageSpeed\": {\n" +
    "              \"units\": \"kph\",\n" +
    "              \"speed\": \"205.155\"\n" +
    "            }\n" +
    "          }\n" +
    "        }\n" +
    "      ]\n" +
    "    },\n" +
    "    {\n" +
    "      \"season\": \"2022\",\n" +
    "      \"round\": \"22\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix\",\n" +
    "      \"raceName\": \"Abu Dhabi Grand Prix\",\n" +
    "      \"Circuit\": {\n" +
    "        \"circuitId\": \"yas_marina\",\n" +
    "        \"url\": \"http://en.wikipedia.org/wiki/Yas_Marina_Circuit\",\n" +
    "        \"circuitName\": \"Yas Marina Circuit\",\n" +
    "        \"Location\": {\n" +
    "          \"lat\": \"24.4672\",\n" +
    "          \"long\": \"54.6031\",\n" +
    "          \"locality\": \"Abu Dhabi\",\n" +
    "          \"country\": \"UAE\"\n" +
    "        }\n" +
    "      },\n" +
    "      \"date\": \"2022-11-20\",\n" +
    "      \"time\": \"13:00:00Z\",\n" +
    "      \"Results\": [\n" +
    "        {\n" +
    "          \"number\": \"23\",\n" +
    "          \"position\": \"13\",\n" +
    "          \"positionText\": \"13\",\n" +
    "          \"points\": \"0\",\n" +
    "          \"Driver\": {\n" +
    "            \"driverId\": \"albon\",\n" +
    "            \"permanentNumber\": \"23\",\n" +
    "            \"code\": \"ALB\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "            \"givenName\": \"Alexander\",\n" +
    "            \"familyName\": \"Albon\",\n" +
    "            \"dateOfBirth\": \"1996-03-23\",\n" +
    "            \"nationality\": \"Thai\"\n" +
    "          },\n" +
    "          \"Constructor\": {\n" +
    "            \"constructorId\": \"williams\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Williams_Grand_Prix_Engineering\",\n" +
    "            \"name\": \"Williams\",\n" +
    "            \"nationality\": \"British\"\n" +
    "          },\n" +
    "          \"grid\": \"19\",\n" +
    "          \"laps\": \"57\",\n" +
    "          \"status\": \"+1 Lap\",\n" +
    "          \"FastestLap\": {\n" +
    "            \"rank\": \"13\",\n" +
    "            \"lap\": \"40\",\n" +
    "            \"Time\": {\n" +
    "              \"time\": \"1:29.939\"\n" +
    "            },\n" +
    "            \"AverageSpeed\": {\n" +
    "              \"units\": \"kph\",\n" +
    "              \"speed\": \"211.383\"\n" +
    "            }\n" +
    "          }\n" +
    "        }\n" +
    "      ]\n" +
    "    }\n" +
    "  ]\n" +
    "}"

private const val INCOMPLETE_SINGLE_MISSING_SEASON = "{\n" +
    "  \"driverId\": \"albon\",\n" +
    "  \"Races\": [\n" +
    "    {\n" +
    "      \"round\": \"22\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix\",\n" +
    "      \"raceName\": \"Abu Dhabi Grand Prix\",\n" +
    "      \"Circuit\": {\n" +
    "        \"circuitId\": \"yas_marina\",\n" +
    "        \"url\": \"http://en.wikipedia.org/wiki/Yas_Marina_Circuit\",\n" +
    "        \"circuitName\": \"Yas Marina Circuit\",\n" +
    "        \"Location\": {\n" +
    "          \"lat\": \"24.4672\",\n" +
    "          \"long\": \"54.6031\",\n" +
    "          \"locality\": \"Abu Dhabi\",\n" +
    "          \"country\": \"UAE\"\n" +
    "        }\n" +
    "      },\n" +
    "      \"date\": \"2022-11-20\",\n" +
    "      \"time\": \"13:00:00Z\",\n" +
    "      \"Results\": [\n" +
    "        {\n" +
    "          \"number\": \"23\",\n" +
    "          \"position\": \"13\",\n" +
    "          \"positionText\": \"13\",\n" +
    "          \"points\": \"0\",\n" +
    "          \"Driver\": {\n" +
    "            \"driverId\": \"albon\",\n" +
    "            \"permanentNumber\": \"23\",\n" +
    "            \"code\": \"ALB\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "            \"givenName\": \"Alexander\",\n" +
    "            \"familyName\": \"Albon\",\n" +
    "            \"dateOfBirth\": \"1996-03-23\",\n" +
    "            \"nationality\": \"Thai\"\n" +
    "          },\n" +
    "          \"Constructor\": {\n" +
    "            \"constructorId\": \"williams\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Williams_Grand_Prix_Engineering\",\n" +
    "            \"name\": \"Williams\",\n" +
    "            \"nationality\": \"British\"\n" +
    "          },\n" +
    "          \"grid\": \"19\",\n" +
    "          \"laps\": \"57\",\n" +
    "          \"status\": \"+1 Lap\",\n" +
    "          \"FastestLap\": {\n" +
    "            \"rank\": \"13\",\n" +
    "            \"lap\": \"40\",\n" +
    "            \"Time\": {\n" +
    "              \"time\": \"1:29.939\"\n" +
    "            },\n" +
    "            \"AverageSpeed\": {\n" +
    "              \"units\": \"kph\",\n" +
    "              \"speed\": \"211.383\"\n" +
    "            }\n" +
    "          }\n" +
    "        }\n" +
    "      ]\n" +
    "    }\n" +
    "  ]\n" +
    "}"

private const val INCOMPLETE_SINGLE_MISSING_ROUND = "{\n" +
    "  \"driverId\": \"albon\",\n" +
    "  \"Races\": [\n" +
    "    {\n" +
    "      \"season\": \"2022\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix\",\n" +
    "      \"raceName\": \"Abu Dhabi Grand Prix\",\n" +
    "      \"Circuit\": {\n" +
    "        \"circuitId\": \"yas_marina\",\n" +
    "        \"url\": \"http://en.wikipedia.org/wiki/Yas_Marina_Circuit\",\n" +
    "        \"circuitName\": \"Yas Marina Circuit\",\n" +
    "        \"Location\": {\n" +
    "          \"lat\": \"24.4672\",\n" +
    "          \"long\": \"54.6031\",\n" +
    "          \"locality\": \"Abu Dhabi\",\n" +
    "          \"country\": \"UAE\"\n" +
    "        }\n" +
    "      },\n" +
    "      \"date\": \"2022-11-20\",\n" +
    "      \"time\": \"13:00:00Z\",\n" +
    "      \"Results\": [\n" +
    "        {\n" +
    "          \"number\": \"23\",\n" +
    "          \"position\": \"13\",\n" +
    "          \"positionText\": \"13\",\n" +
    "          \"points\": \"0\",\n" +
    "          \"Driver\": {\n" +
    "            \"driverId\": \"albon\",\n" +
    "            \"permanentNumber\": \"23\",\n" +
    "            \"code\": \"ALB\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "            \"givenName\": \"Alexander\",\n" +
    "            \"familyName\": \"Albon\",\n" +
    "            \"dateOfBirth\": \"1996-03-23\",\n" +
    "            \"nationality\": \"Thai\"\n" +
    "          },\n" +
    "          \"Constructor\": {\n" +
    "            \"constructorId\": \"williams\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Williams_Grand_Prix_Engineering\",\n" +
    "            \"name\": \"Williams\",\n" +
    "            \"nationality\": \"British\"\n" +
    "          },\n" +
    "          \"grid\": \"19\",\n" +
    "          \"laps\": \"57\",\n" +
    "          \"status\": \"+1 Lap\",\n" +
    "          \"FastestLap\": {\n" +
    "            \"rank\": \"13\",\n" +
    "            \"lap\": \"40\",\n" +
    "            \"Time\": {\n" +
    "              \"time\": \"1:29.939\"\n" +
    "            },\n" +
    "            \"AverageSpeed\": {\n" +
    "              \"units\": \"kph\",\n" +
    "              \"speed\": \"211.383\"\n" +
    "            }\n" +
    "          }\n" +
    "        }\n" +
    "      ]\n" +
    "    }\n" +
    "  ]\n" +
    "}"

private const val INCOMPLETE_SINGLE_MISSING_POSITION = "{\n" +
    "  \"driverId\": \"albon\",\n" +
    "  \"Races\": [\n" +
    "    {\n" +
    "      \"season\": \"2022\",\n" +
    "      \"round\": \"22\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix\",\n" +
    "      \"raceName\": \"Abu Dhabi Grand Prix\",\n" +
    "      \"Circuit\": {\n" +
    "        \"circuitId\": \"yas_marina\",\n" +
    "        \"url\": \"http://en.wikipedia.org/wiki/Yas_Marina_Circuit\",\n" +
    "        \"circuitName\": \"Yas Marina Circuit\",\n" +
    "        \"Location\": {\n" +
    "          \"lat\": \"24.4672\",\n" +
    "          \"long\": \"54.6031\",\n" +
    "          \"locality\": \"Abu Dhabi\",\n" +
    "          \"country\": \"UAE\"\n" +
    "        }\n" +
    "      },\n" +
    "      \"date\": \"2022-11-20\",\n" +
    "      \"time\": \"13:00:00Z\",\n" +
    "      \"Results\": [\n" +
    "        {\n" +
    "          \"number\": \"23\",\n" +
    "          \"positionText\": \"13\",\n" +
    "          \"points\": \"0\",\n" +
    "          \"Driver\": {\n" +
    "            \"driverId\": \"albon\",\n" +
    "            \"permanentNumber\": \"23\",\n" +
    "            \"code\": \"ALB\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "            \"givenName\": \"Alexander\",\n" +
    "            \"familyName\": \"Albon\",\n" +
    "            \"dateOfBirth\": \"1996-03-23\",\n" +
    "            \"nationality\": \"Thai\"\n" +
    "          },\n" +
    "          \"Constructor\": {\n" +
    "            \"constructorId\": \"williams\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Williams_Grand_Prix_Engineering\",\n" +
    "            \"name\": \"Williams\",\n" +
    "            \"nationality\": \"British\"\n" +
    "          },\n" +
    "          \"grid\": \"19\",\n" +
    "          \"laps\": \"57\",\n" +
    "          \"status\": \"+1 Lap\",\n" +
    "          \"FastestLap\": {\n" +
    "            \"rank\": \"13\",\n" +
    "            \"lap\": \"40\",\n" +
    "            \"Time\": {\n" +
    "              \"time\": \"1:29.939\"\n" +
    "            },\n" +
    "            \"AverageSpeed\": {\n" +
    "              \"units\": \"kph\",\n" +
    "              \"speed\": \"211.383\"\n" +
    "            }\n" +
    "          }\n" +
    "        }\n" +
    "      ]\n" +
    "    }\n" +
    "  ]\n" +
    "}"

private const val INCOMPLETE_SINGLE_MISSING_POINTS = "{\n" +
    "  \"driverId\": \"albon\",\n" +
    "  \"Races\": [\n" +
    "    {\n" +
    "      \"season\": \"2022\",\n" +
    "      \"round\": \"22\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix\",\n" +
    "      \"raceName\": \"Abu Dhabi Grand Prix\",\n" +
    "      \"Circuit\": {\n" +
    "        \"circuitId\": \"yas_marina\",\n" +
    "        \"url\": \"http://en.wikipedia.org/wiki/Yas_Marina_Circuit\",\n" +
    "        \"circuitName\": \"Yas Marina Circuit\",\n" +
    "        \"Location\": {\n" +
    "          \"lat\": \"24.4672\",\n" +
    "          \"long\": \"54.6031\",\n" +
    "          \"locality\": \"Abu Dhabi\",\n" +
    "          \"country\": \"UAE\"\n" +
    "        }\n" +
    "      },\n" +
    "      \"date\": \"2022-11-20\",\n" +
    "      \"time\": \"13:00:00Z\",\n" +
    "      \"Results\": [\n" +
    "        {\n" +
    "          \"number\": \"23\",\n" +
    "          \"position\": \"13\",\n" +
    "          \"positionText\": \"13\",\n" +
    "          \"Driver\": {\n" +
    "            \"driverId\": \"albon\",\n" +
    "            \"permanentNumber\": \"23\",\n" +
    "            \"code\": \"ALB\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "            \"givenName\": \"Alexander\",\n" +
    "            \"familyName\": \"Albon\",\n" +
    "            \"dateOfBirth\": \"1996-03-23\",\n" +
    "            \"nationality\": \"Thai\"\n" +
    "          },\n" +
    "          \"Constructor\": {\n" +
    "            \"constructorId\": \"williams\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Williams_Grand_Prix_Engineering\",\n" +
    "            \"name\": \"Williams\",\n" +
    "            \"nationality\": \"British\"\n" +
    "          },\n" +
    "          \"grid\": \"19\",\n" +
    "          \"laps\": \"57\",\n" +
    "          \"status\": \"+1 Lap\",\n" +
    "          \"FastestLap\": {\n" +
    "            \"rank\": \"13\",\n" +
    "            \"lap\": \"40\",\n" +
    "            \"Time\": {\n" +
    "              \"time\": \"1:29.939\"\n" +
    "            },\n" +
    "            \"AverageSpeed\": {\n" +
    "              \"units\": \"kph\",\n" +
    "              \"speed\": \"211.383\"\n" +
    "            }\n" +
    "          }\n" +
    "        }\n" +
    "      ]\n" +
    "    }\n" +
    "  ]\n" +
    "}"

private const val INCOMPLETE_SINGLE_MISSING_GRID = "{\n" +
    "  \"driverId\": \"albon\",\n" +
    "  \"Races\": [\n" +
    "    {\n" +
    "      \"season\": \"2022\",\n" +
    "      \"round\": \"22\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix\",\n" +
    "      \"raceName\": \"Abu Dhabi Grand Prix\",\n" +
    "      \"Circuit\": {\n" +
    "        \"circuitId\": \"yas_marina\",\n" +
    "        \"url\": \"http://en.wikipedia.org/wiki/Yas_Marina_Circuit\",\n" +
    "        \"circuitName\": \"Yas Marina Circuit\",\n" +
    "        \"Location\": {\n" +
    "          \"lat\": \"24.4672\",\n" +
    "          \"long\": \"54.6031\",\n" +
    "          \"locality\": \"Abu Dhabi\",\n" +
    "          \"country\": \"UAE\"\n" +
    "        }\n" +
    "      },\n" +
    "      \"date\": \"2022-11-20\",\n" +
    "      \"time\": \"13:00:00Z\",\n" +
    "      \"Results\": [\n" +
    "        {\n" +
    "          \"number\": \"23\",\n" +
    "          \"position\": \"13\",\n" +
    "          \"positionText\": \"13\",\n" +
    "          \"points\": \"0\",\n" +
    "          \"Driver\": {\n" +
    "            \"driverId\": \"albon\",\n" +
    "            \"permanentNumber\": \"23\",\n" +
    "            \"code\": \"ALB\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "            \"givenName\": \"Alexander\",\n" +
    "            \"familyName\": \"Albon\",\n" +
    "            \"dateOfBirth\": \"1996-03-23\",\n" +
    "            \"nationality\": \"Thai\"\n" +
    "          },\n" +
    "          \"Constructor\": {\n" +
    "            \"constructorId\": \"williams\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Williams_Grand_Prix_Engineering\",\n" +
    "            \"name\": \"Williams\",\n" +
    "            \"nationality\": \"British\"\n" +
    "          },\n" +
    "          \"laps\": \"57\",\n" +
    "          \"status\": \"+1 Lap\",\n" +
    "          \"FastestLap\": {\n" +
    "            \"rank\": \"13\",\n" +
    "            \"lap\": \"40\",\n" +
    "            \"Time\": {\n" +
    "              \"time\": \"1:29.939\"\n" +
    "            },\n" +
    "            \"AverageSpeed\": {\n" +
    "              \"units\": \"kph\",\n" +
    "              \"speed\": \"211.383\"\n" +
    "            }\n" +
    "          }\n" +
    "        }\n" +
    "      ]\n" +
    "    }\n" +
    "  ]\n" +
    "}"

private const val INCOMPLETE_SINGLE_MISSING_LAPS = "{\n" +
    "  \"driverId\": \"albon\",\n" +
    "  \"Races\": [\n" +
    "    {\n" +
    "      \"season\": \"2022\",\n" +
    "      \"round\": \"22\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix\",\n" +
    "      \"raceName\": \"Abu Dhabi Grand Prix\",\n" +
    "      \"Circuit\": {\n" +
    "        \"circuitId\": \"yas_marina\",\n" +
    "        \"url\": \"http://en.wikipedia.org/wiki/Yas_Marina_Circuit\",\n" +
    "        \"circuitName\": \"Yas Marina Circuit\",\n" +
    "        \"Location\": {\n" +
    "          \"lat\": \"24.4672\",\n" +
    "          \"long\": \"54.6031\",\n" +
    "          \"locality\": \"Abu Dhabi\",\n" +
    "          \"country\": \"UAE\"\n" +
    "        }\n" +
    "      },\n" +
    "      \"date\": \"2022-11-20\",\n" +
    "      \"time\": \"13:00:00Z\",\n" +
    "      \"Results\": [\n" +
    "        {\n" +
    "          \"number\": \"23\",\n" +
    "          \"position\": \"13\",\n" +
    "          \"positionText\": \"13\",\n" +
    "          \"points\": \"0\",\n" +
    "          \"Driver\": {\n" +
    "            \"driverId\": \"albon\",\n" +
    "            \"permanentNumber\": \"23\",\n" +
    "            \"code\": \"ALB\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "            \"givenName\": \"Alexander\",\n" +
    "            \"familyName\": \"Albon\",\n" +
    "            \"dateOfBirth\": \"1996-03-23\",\n" +
    "            \"nationality\": \"Thai\"\n" +
    "          },\n" +
    "          \"Constructor\": {\n" +
    "            \"constructorId\": \"williams\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Williams_Grand_Prix_Engineering\",\n" +
    "            \"name\": \"Williams\",\n" +
    "            \"nationality\": \"British\"\n" +
    "          },\n" +
    "          \"grid\": \"19\",\n" +
    "          \"status\": \"+1 Lap\",\n" +
    "          \"FastestLap\": {\n" +
    "            \"rank\": \"13\",\n" +
    "            \"lap\": \"40\",\n" +
    "            \"Time\": {\n" +
    "              \"time\": \"1:29.939\"\n" +
    "            },\n" +
    "            \"AverageSpeed\": {\n" +
    "              \"units\": \"kph\",\n" +
    "              \"speed\": \"211.383\"\n" +
    "            }\n" +
    "          }\n" +
    "        }\n" +
    "      ]\n" +
    "    }\n" +
    "  ]\n" +
    "}"

private const val INCOMPLETE_SINGLE_MISSING_STATUS = "{\n" +
    "  \"driverId\": \"albon\",\n" +
    "  \"Races\": [\n" +
    "    {\n" +
    "      \"season\": \"2022\",\n" +
    "      \"round\": \"22\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix\",\n" +
    "      \"raceName\": \"Abu Dhabi Grand Prix\",\n" +
    "      \"Circuit\": {\n" +
    "        \"circuitId\": \"yas_marina\",\n" +
    "        \"url\": \"http://en.wikipedia.org/wiki/Yas_Marina_Circuit\",\n" +
    "        \"circuitName\": \"Yas Marina Circuit\",\n" +
    "        \"Location\": {\n" +
    "          \"lat\": \"24.4672\",\n" +
    "          \"long\": \"54.6031\",\n" +
    "          \"locality\": \"Abu Dhabi\",\n" +
    "          \"country\": \"UAE\"\n" +
    "        }\n" +
    "      },\n" +
    "      \"date\": \"2022-11-20\",\n" +
    "      \"time\": \"13:00:00Z\",\n" +
    "      \"Results\": [\n" +
    "        {\n" +
    "          \"number\": \"23\",\n" +
    "          \"position\": \"13\",\n" +
    "          \"positionText\": \"13\",\n" +
    "          \"points\": \"0\",\n" +
    "          \"Driver\": {\n" +
    "            \"driverId\": \"albon\",\n" +
    "            \"permanentNumber\": \"23\",\n" +
    "            \"code\": \"ALB\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "            \"givenName\": \"Alexander\",\n" +
    "            \"familyName\": \"Albon\",\n" +
    "            \"dateOfBirth\": \"1996-03-23\",\n" +
    "            \"nationality\": \"Thai\"\n" +
    "          },\n" +
    "          \"Constructor\": {\n" +
    "            \"constructorId\": \"williams\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Williams_Grand_Prix_Engineering\",\n" +
    "            \"name\": \"Williams\",\n" +
    "            \"nationality\": \"British\"\n" +
    "          },\n" +
    "          \"grid\": \"19\",\n" +
    "          \"laps\": \"57\",\n" +
    "          \"FastestLap\": {\n" +
    "            \"rank\": \"13\",\n" +
    "            \"lap\": \"40\",\n" +
    "            \"Time\": {\n" +
    "              \"time\": \"1:29.939\"\n" +
    "            },\n" +
    "            \"AverageSpeed\": {\n" +
    "              \"units\": \"kph\",\n" +
    "              \"speed\": \"211.383\"\n" +
    "            }\n" +
    "          }\n" +
    "        }\n" +
    "      ]\n" +
    "    }\n" +
    "  ]\n" +
    "}"

private const val INCOMPLETE_SINGLE_MISSING_FASTESTLAP = "{\n" +
    "  \"driverId\": \"albon\",\n" +
    "  \"Races\": [\n" +
    "    {\n" +
    "      \"season\": \"2022\",\n" +
    "      \"round\": \"22\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix\",\n" +
    "      \"raceName\": \"Abu Dhabi Grand Prix\",\n" +
    "      \"Circuit\": {\n" +
    "        \"circuitId\": \"yas_marina\",\n" +
    "        \"url\": \"http://en.wikipedia.org/wiki/Yas_Marina_Circuit\",\n" +
    "        \"circuitName\": \"Yas Marina Circuit\",\n" +
    "        \"Location\": {\n" +
    "          \"lat\": \"24.4672\",\n" +
    "          \"long\": \"54.6031\",\n" +
    "          \"locality\": \"Abu Dhabi\",\n" +
    "          \"country\": \"UAE\"\n" +
    "        }\n" +
    "      },\n" +
    "      \"date\": \"2022-11-20\",\n" +
    "      \"time\": \"13:00:00Z\",\n" +
    "      \"Results\": [\n" +
    "        {\n" +
    "          \"number\": \"23\",\n" +
    "          \"position\": \"13\",\n" +
    "          \"positionText\": \"13\",\n" +
    "          \"points\": \"0\",\n" +
    "          \"Driver\": {\n" +
    "            \"driverId\": \"albon\",\n" +
    "            \"permanentNumber\": \"23\",\n" +
    "            \"code\": \"ALB\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "            \"givenName\": \"Alexander\",\n" +
    "            \"familyName\": \"Albon\",\n" +
    "            \"dateOfBirth\": \"1996-03-23\",\n" +
    "            \"nationality\": \"Thai\"\n" +
    "          },\n" +
    "          \"Constructor\": {\n" +
    "            \"constructorId\": \"williams\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Williams_Grand_Prix_Engineering\",\n" +
    "            \"name\": \"Williams\",\n" +
    "            \"nationality\": \"British\"\n" +
    "          },\n" +
    "          \"grid\": \"19\",\n" +
    "          \"laps\": \"57\",\n" +
    "          \"status\": \"+1 Lap\",\n" +
    "          \"FastestLap\": {\n" +
    "            \"rank\": \"13\",\n" +
    "            \"lap\": \"40\",\n" +
    "            \"Time\": {\n" +
    "            },\n" +
    "            \"AverageSpeed\": {\n" +
    "              \"units\": \"kph\",\n" +
    "              \"speed\": \"211.383\"\n" +
    "            }\n" +
    "          }\n" +
    "        }\n" +
    "      ]\n" +
    "    }\n" +
    "  ]\n" +
    "}"

private const val INCOMPLETE_SINGLE_MISSING_FASTESTLAP_RANK = "{\n" +
    "  \"driverId\": \"albon\",\n" +
    "  \"Races\": [\n" +
    "    {\n" +
    "      \"season\": \"2022\",\n" +
    "      \"round\": \"22\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix\",\n" +
    "      \"raceName\": \"Abu Dhabi Grand Prix\",\n" +
    "      \"Circuit\": {\n" +
    "        \"circuitId\": \"yas_marina\",\n" +
    "        \"url\": \"http://en.wikipedia.org/wiki/Yas_Marina_Circuit\",\n" +
    "        \"circuitName\": \"Yas Marina Circuit\",\n" +
    "        \"Location\": {\n" +
    "          \"lat\": \"24.4672\",\n" +
    "          \"long\": \"54.6031\",\n" +
    "          \"locality\": \"Abu Dhabi\",\n" +
    "          \"country\": \"UAE\"\n" +
    "        }\n" +
    "      },\n" +
    "      \"date\": \"2022-11-20\",\n" +
    "      \"time\": \"13:00:00Z\",\n" +
    "      \"Results\": [\n" +
    "        {\n" +
    "          \"number\": \"23\",\n" +
    "          \"position\": \"13\",\n" +
    "          \"positionText\": \"13\",\n" +
    "          \"points\": \"0\",\n" +
    "          \"Driver\": {\n" +
    "            \"driverId\": \"albon\",\n" +
    "            \"permanentNumber\": \"23\",\n" +
    "            \"code\": \"ALB\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "            \"givenName\": \"Alexander\",\n" +
    "            \"familyName\": \"Albon\",\n" +
    "            \"dateOfBirth\": \"1996-03-23\",\n" +
    "            \"nationality\": \"Thai\"\n" +
    "          },\n" +
    "          \"Constructor\": {\n" +
    "            \"constructorId\": \"williams\",\n" +
    "            \"url\": \"http://en.wikipedia.org/wiki/Williams_Grand_Prix_Engineering\",\n" +
    "            \"name\": \"Williams\",\n" +
    "            \"nationality\": \"British\"\n" +
    "          },\n" +
    "          \"grid\": \"19\",\n" +
    "          \"laps\": \"57\",\n" +
    "          \"status\": \"+1 Lap\",\n" +
    "          \"FastestLap\": {\n" +
    "            \"lap\": \"40\",\n" +
    "            \"Time\": {\n" +
    "              \"time\": \"1:29.939\"\n" +
    "            },\n" +
    "            \"AverageSpeed\": {\n" +
    "              \"units\": \"kph\",\n" +
    "              \"speed\": \"211.383\"\n" +
    "            }\n" +
    "          }\n" +
    "        }\n" +
    "      ]\n" +
    "    }\n" +
    "  ]\n" +
    "}"
