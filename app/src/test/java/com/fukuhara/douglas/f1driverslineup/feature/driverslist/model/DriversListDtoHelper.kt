package com.fukuhara.douglas.f1driverslineup.feature.driverslist.model

import com.fukuhara.douglas.f1driverslineup.feature.driverslist.repository.dto.DriversListDto
import com.google.gson.Gson

object DriversListDtoHelper {
    private fun jsonToDto(json: String): DriversListDto = Gson().fromJson(json, DriversListDto::class.java)

    fun generateCompleteSingleElement(): DriversListDto = jsonToDto(COMPLETE_SINGLE_ELEMENT)
    fun generateMissingSeasonSingleElement(): DriversListDto = jsonToDto(MISSING_SEASON_SINGLE_ITEM)
    fun generateMissingDriverIdSingleElement(): DriversListDto = jsonToDto(MISSING_DRIVERID_SINGLE_ITEM)
    fun generateMissingPermanentNumberSingleElement(): DriversListDto = jsonToDto(MISSING_PERMANENTNUMBER_SINGLE_ELEMENT)
    fun generateInvalidPermanentNumberSingleElement(): DriversListDto = jsonToDto(INVALID_PERMANENTNUMBER_SINGLE_ELEMENT)
    fun generateMissingImageUrlSingleElement(): DriversListDto = jsonToDto(MISSING_IMAGE_URL_SINGLE_ELEMENT)
    fun generateMissingGivenNameSingleElement(): DriversListDto = jsonToDto(MISSING_GIVEN_NAME_SINGLE_ELEMENT)
    fun generateMissingFamilyNameSingleElement(): DriversListDto = jsonToDto(MISSING_FAMILY_NAME_SINGLE_ELEMENT)
    fun generateMissingDateOfBirthSingleElement(): DriversListDto = jsonToDto(MISSING_DATEOFBIRTH_SINGLE_ELEMENT)
    fun generateMissingTeamSingleElement(): DriversListDto = jsonToDto(MISSING_TEAM_SINGLE_ELEMENT)
    fun generateMissingTeamColorSingleElement(): DriversListDto = jsonToDto(MISSING_TEAMCOLOR_SINGLE_ELEMENT)
    fun generateMissingNationalitySingleElement(): DriversListDto = jsonToDto(MISSING_NATIONALITY_SINGLE_ELEMENT)
    fun generateCompleteThreeElements(): DriversListDto = jsonToDto(THREE_COMPLETE_ELEMENTS)
    fun generateTwoCompleteOneMissingFamilyNameElements(): DriversListDto = jsonToDto(TWO_COMPLETE_ONE_MISSING_FAMILYNAME_ELEMENTS)
}

private const val COMPLETE_SINGLE_ELEMENT = "{\n" +
    "  \"season\": \"2022\",\n" +
    "  \"total\": \"1\",\n" +
    "  \"Drivers\": \n" +
    "  [\n" +
    "    {\n" +
    "      \"driverId\": \"albon\",\n" +
    "      \"permanentNumber\": \"23\",\n" +
    "      \"code\": \"ALB\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "      \"imageUrl\": \"https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/williams/albon.png\",\n" +
    "      \"givenName\": \"Alexander\",\n" +
    "      \"familyName\": \"Albon\",\n" +
    "      \"dateOfBirth\": \"1996-03-23\",\n" +
    "      \"team\": \"Williams\",\n" +
    "      \"teamColor\": \"#37BEDD\",\n" +
    "      \"nationality\": \"TH\"\n" +
    "    }]}"

private const val MISSING_SEASON_SINGLE_ITEM = "{\n" +
    "  \"total\": \"1\",\n" +
    "  \"Drivers\": \n" +
    "  [\n" +
    "    {\n" +
    "      \"driverId\": \"albon\",\n" +
    "      \"permanentNumber\": \"23\",\n" +
    "      \"code\": \"ALB\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "      \"imageUrl\": \"https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/williams/albon.png\",\n" +
    "      \"givenName\": \"Alexander\",\n" +
    "      \"familyName\": \"Albon\",\n" +
    "      \"dateOfBirth\": \"1996-03-23\",\n" +
    "      \"team\": \"Williams\",\n" +
    "      \"teamColor\": \"#37BEDD\",\n" +
    "      \"nationality\": \"TH\"\n" +
    "    }]}"

private const val MISSING_DRIVERID_SINGLE_ITEM = "{\n" +
    "  \"season\": \"2022\",\n" +
    "  \"total\": \"1\",\n" +
    "  \"Drivers\": \n" +
    "  [\n" +
    "    {\n" +
    "      \"permanentNumber\": \"23\",\n" +
    "      \"code\": \"ALB\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "      \"imageUrl\": \"https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/williams/albon.png\",\n" +
    "      \"givenName\": \"Alexander\",\n" +
    "      \"familyName\": \"Albon\",\n" +
    "      \"dateOfBirth\": \"1996-03-23\",\n" +
    "      \"team\": \"Williams\",\n" +
    "      \"teamColor\": \"#37BEDD\",\n" +
    "      \"nationality\": \"TH\"\n" +
    "    }]}"

private const val MISSING_PERMANENTNUMBER_SINGLE_ELEMENT = "{\n" +
    "  \"season\": \"2022\",\n" +
    "  \"total\": \"1\",\n" +
    "  \"Drivers\": \n" +
    "  [\n" +
    "    {\n" +
    "      \"driverId\": \"albon\",\n" +
    "      \"code\": \"ALB\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "      \"imageUrl\": \"https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/williams/albon.png\",\n" +
    "      \"givenName\": \"Alexander\",\n" +
    "      \"familyName\": \"Albon\",\n" +
    "      \"dateOfBirth\": \"1996-03-23\",\n" +
    "      \"team\": \"Williams\",\n" +
    "      \"teamColor\": \"#37BEDD\",\n" +
    "      \"nationality\": \"TH\"\n" +
    "    }]}"

private const val INVALID_PERMANENTNUMBER_SINGLE_ELEMENT = "{\n" +
    "  \"season\": \"2022\",\n" +
    "  \"total\": \"1\",\n" +
    "  \"Drivers\": \n" +
    "  [\n" +
    "    {\n" +
    "      \"driverId\": \"albon\",\n" +
    "      \"permanentNumber\": \"TwentyThree\",\n" +
    "      \"code\": \"ALB\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "      \"imageUrl\": \"https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/williams/albon.png\",\n" +
    "      \"givenName\": \"Alexander\",\n" +
    "      \"familyName\": \"Albon\",\n" +
    "      \"dateOfBirth\": \"1996-03-23\",\n" +
    "      \"team\": \"Williams\",\n" +
    "      \"teamColor\": \"#37BEDD\",\n" +
    "      \"nationality\": \"TH\"\n" +
    "    }]}"

private const val MISSING_IMAGE_URL_SINGLE_ELEMENT = "{\n" +
    "  \"season\": \"2022\",\n" +
    "  \"total\": \"1\",\n" +
    "  \"Drivers\": \n" +
    "  [\n" +
    "    {\n" +
    "      \"driverId\": \"albon\",\n" +
    "      \"permanentNumber\": \"23\",\n" +
    "      \"code\": \"ALB\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "      \"givenName\": \"Alexander\",\n" +
    "      \"familyName\": \"Albon\",\n" +
    "      \"dateOfBirth\": \"1996-03-23\",\n" +
    "      \"team\": \"Williams\",\n" +
    "      \"teamColor\": \"#37BEDD\",\n" +
    "      \"nationality\": \"TH\"\n" +
    "    }]}"

private const val MISSING_GIVEN_NAME_SINGLE_ELEMENT = "{\n" +
    "  \"season\": \"2022\",\n" +
    "  \"total\": \"1\",\n" +
    "  \"Drivers\": \n" +
    "  [\n" +
    "    {\n" +
    "      \"driverId\": \"albon\",\n" +
    "      \"permanentNumber\": \"23\",\n" +
    "      \"code\": \"ALB\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "      \"imageUrl\": \"https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/williams/albon.png\",\n" +
    "      \"familyName\": \"Albon\",\n" +
    "      \"dateOfBirth\": \"1996-03-23\",\n" +
    "      \"team\": \"Williams\",\n" +
    "      \"teamColor\": \"#37BEDD\",\n" +
    "      \"nationality\": \"TH\"\n" +
    "    }]}"

private const val MISSING_FAMILY_NAME_SINGLE_ELEMENT = "{\n" +
    "  \"season\": \"2022\",\n" +
    "  \"total\": \"1\",\n" +
    "  \"Drivers\": \n" +
    "  [\n" +
    "    {\n" +
    "      \"driverId\": \"albon\",\n" +
    "      \"permanentNumber\": \"23\",\n" +
    "      \"code\": \"ALB\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "      \"imageUrl\": \"https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/williams/albon.png\",\n" +
    "      \"givenName\": \"Alexander\",\n" +
    "      \"dateOfBirth\": \"1996-03-23\",\n" +
    "      \"team\": \"Williams\",\n" +
    "      \"teamColor\": \"#37BEDD\",\n" +
    "      \"nationality\": \"TH\"\n" +
    "    }]}"

private const val MISSING_DATEOFBIRTH_SINGLE_ELEMENT = "{\n" +
    "  \"season\": \"2022\",\n" +
    "  \"total\": \"1\",\n" +
    "  \"Drivers\": \n" +
    "  [\n" +
    "    {\n" +
    "      \"driverId\": \"albon\",\n" +
    "      \"permanentNumber\": \"23\",\n" +
    "      \"code\": \"ALB\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "      \"imageUrl\": \"https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/williams/albon.png\",\n" +
    "      \"givenName\": \"Alexander\",\n" +
    "      \"familyName\": \"Albon\",\n" +
    "      \"team\": \"Williams\",\n" +
    "      \"teamColor\": \"#37BEDD\",\n" +
    "      \"nationality\": \"TH\"\n" +
    "    }]}"

private const val MISSING_TEAM_SINGLE_ELEMENT = "{\n" +
    "  \"season\": \"2022\",\n" +
    "  \"total\": \"1\",\n" +
    "  \"Drivers\": \n" +
    "  [\n" +
    "    {\n" +
    "      \"driverId\": \"albon\",\n" +
    "      \"permanentNumber\": \"23\",\n" +
    "      \"code\": \"ALB\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "      \"imageUrl\": \"https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/williams/albon.png\",\n" +
    "      \"givenName\": \"Alexander\",\n" +
    "      \"familyName\": \"Albon\",\n" +
    "      \"dateOfBirth\": \"1996-03-23\",\n" +
    "      \"teamColor\": \"#37BEDD\",\n" +
    "      \"nationality\": \"TH\"\n" +
    "    }]}"

private const val MISSING_TEAMCOLOR_SINGLE_ELEMENT = "{\n" +
    "  \"season\": \"2022\",\n" +
    "  \"total\": \"1\",\n" +
    "  \"Drivers\": \n" +
    "  [\n" +
    "    {\n" +
    "      \"driverId\": \"albon\",\n" +
    "      \"permanentNumber\": \"23\",\n" +
    "      \"code\": \"ALB\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "      \"imageUrl\": \"https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/williams/albon.png\",\n" +
    "      \"givenName\": \"Alexander\",\n" +
    "      \"familyName\": \"Albon\",\n" +
    "      \"dateOfBirth\": \"1996-03-23\",\n" +
    "      \"team\": \"Williams\",\n" +
    "      \"nationality\": \"TH\"\n" +
    "    }]}"

private const val MISSING_NATIONALITY_SINGLE_ELEMENT = "{\n" +
    "  \"season\": \"2022\",\n" +
    "  \"total\": \"1\",\n" +
    "  \"Drivers\": \n" +
    "  [\n" +
    "    {\n" +
    "      \"driverId\": \"albon\",\n" +
    "      \"permanentNumber\": \"23\",\n" +
    "      \"code\": \"ALB\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "      \"imageUrl\": \"https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/williams/albon.png\",\n" +
    "      \"givenName\": \"Alexander\",\n" +
    "      \"familyName\": \"Albon\",\n" +
    "      \"dateOfBirth\": \"1996-03-23\",\n" +
    "      \"team\": \"Williams\",\n" +
    "      \"teamColor\": \"#37BEDD\"\n" +
    "    }]}"

private const val THREE_COMPLETE_ELEMENTS = "{\n" +
    "  \"season\": \"2022\",\n" +
    "  \"total\": \"3\",\n" +
    "  \"Drivers\": \n" +
    "  [\n" +
    "    {\n" +
    "      \"driverId\": \"albon\",\n" +
    "      \"permanentNumber\": \"23\",\n" +
    "      \"code\": \"ALB\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "      \"imageUrl\": \"https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/williams/albon.png\",\n" +
    "      \"givenName\": \"Alexander\",\n" +
    "      \"familyName\": \"Albon\",\n" +
    "      \"dateOfBirth\": \"1996-03-23\",\n" +
    "      \"team\": \"Williams\",\n" +
    "      \"teamColor\": \"#37BEDD\",\n" +
    "      \"nationality\": \"TH\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"driverId\": \"alonso\",\n" +
    "      \"permanentNumber\": \"14\",\n" +
    "      \"code\": \"ALO\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/Fernando_Alonso\",\n" +
    "      \"imageUrl\": \"https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/alpine/alonso.jpeg\",\n" +
    "      \"givenName\": \"Fernando\",\n" +
    "      \"familyName\": \"Alonso\",\n" +
    "      \"dateOfBirth\": \"1981-07-29\",\n" +
    "      \"team\": \"Alpine\",\n" +
    "      \"teamColor\": \"#2293D1\",\n" +
    "      \"nationality\": \"ES\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"driverId\": \"bottas\",\n" +
    "      \"permanentNumber\": \"77\",\n" +
    "      \"code\": \"BOT\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/Valtteri_Bottas\",\n" +
    "      \"imageUrl\": \"https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/alfa_romeo/bottas.webp\",\n" +
    "      \"givenName\": \"Valtteri\",\n" +
    "      \"familyName\": \"Bottas\",\n" +
    "      \"dateOfBirth\": \"1989-08-28\",\n" +
    "      \"team\": \"Alfa Romeo\",\n" +
    "      \"teamColor\": \"#C92D4B\",\n" +
    "      \"nationality\": \"FI\"\n" +
    "    }]}"

private const val TWO_COMPLETE_ONE_MISSING_FAMILYNAME_ELEMENTS = "{\n" +
    "  \"season\": \"2022\",\n" +
    "  \"total\": \"3\",\n" +
    "  \"Drivers\": \n" +
    "  [\n" +
    "    {\n" +
    "      \"driverId\": \"albon\",\n" +
    "      \"permanentNumber\": \"23\",\n" +
    "      \"code\": \"ALB\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/Alexander_Albon\",\n" +
    "      \"imageUrl\": \"https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/williams/albon.png\",\n" +
    "      \"givenName\": \"Alexander\",\n" +
    "      \"familyName\": \"Albon\",\n" +
    "      \"dateOfBirth\": \"1996-03-23\",\n" +
    "      \"team\": \"Williams\",\n" +
    "      \"teamColor\": \"#37BEDD\",\n" +
    "      \"nationality\": \"Thai\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"driverId\": \"alonso\",\n" +
    "      \"permanentNumber\": \"14\",\n" +
    "      \"code\": \"ALO\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/Fernando_Alonso\",\n" +
    "      \"imageUrl\": \"https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/alpine/alonso.jpeg\",\n" +
    "      \"givenName\": \"Fernando\",\n" +
    "      \"dateOfBirth\": \"1981-07-29\",\n" +
    "      \"team\": \"Alpine\",\n" +
    "      \"teamColor\": \"#2293D1\",\n" +
    "      \"nationality\": \"Spanish\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"driverId\": \"bottas\",\n" +
    "      \"permanentNumber\": \"77\",\n" +
    "      \"code\": \"BOT\",\n" +
    "      \"url\": \"http://en.wikipedia.org/wiki/Valtteri_Bottas\",\n" +
    "      \"imageUrl\": \"https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/alfa_romeo/bottas.webp\",\n" +
    "      \"givenName\": \"Valtteri\",\n" +
    "      \"familyName\": \"Bottas\",\n" +
    "      \"dateOfBirth\": \"1989-08-28\",\n" +
    "      \"team\": \"Alfa Romeo\",\n" +
    "      \"teamColor\": \"#C92D4B\",\n" +
    "      \"nationality\": \"Finnish\"\n" +
    "    }]}"
