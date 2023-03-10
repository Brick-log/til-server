package com.tenmm.tilserver.common.constants

const val UUID_REGEX_VALUE = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$"
val UUID_REGEX = UUID_REGEX_VALUE.toRegex()

const val URL_REGEX_VALUE =
    "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)"
val URL_REGEX = URL_REGEX_VALUE.toRegex()

const val CRON_ITERATION_REGEX_VALIE = "(@(annually|yearly|monthly|weekly|daily|hourly|reboot))|(@every (\\d+(ns|us|µs|ms|s|m|h))+)|((((\\d+,)+\\d+|(\\d+(\\/|-)\\d+)|\\d+|\\*) ?){5,7})"
val CRON_ITERATION_REGEX = CRON_ITERATION_REGEX_VALIE.toRegex()
