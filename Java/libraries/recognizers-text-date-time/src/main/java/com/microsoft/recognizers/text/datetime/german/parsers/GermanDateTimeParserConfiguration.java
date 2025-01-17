// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.recognizers.text.datetime.german.parsers;

import com.google.common.collect.ImmutableMap;
import com.microsoft.recognizers.text.IExtractor;
import com.microsoft.recognizers.text.IParser;
import com.microsoft.recognizers.text.datetime.Constants;
import com.microsoft.recognizers.text.datetime.config.BaseOptionsConfiguration;
import com.microsoft.recognizers.text.datetime.extractors.IDateTimeExtractor;
import com.microsoft.recognizers.text.datetime.extractors.config.ResultTimex;
import com.microsoft.recognizers.text.datetime.german.extractors.GermanDateTimeExtractorConfiguration;
import com.microsoft.recognizers.text.datetime.german.extractors.GermanTimeExtractorConfiguration;
import com.microsoft.recognizers.text.datetime.parsers.IDateTimeParser;
import com.microsoft.recognizers.text.datetime.parsers.config.ICommonDateTimeParserConfiguration;
import com.microsoft.recognizers.text.datetime.parsers.config.IDateTimeParserConfiguration;
import com.microsoft.recognizers.text.datetime.resources.GermanDateTime;
import com.microsoft.recognizers.text.datetime.utilities.IDateTimeUtilityConfiguration;
import com.microsoft.recognizers.text.utilities.RegExpUtility;

import java.util.regex.Pattern;

public class GermanDateTimeParserConfiguration extends BaseOptionsConfiguration implements IDateTimeParserConfiguration {

    private final String tokenBeforeDate;
    private final String tokenBeforeTime;

    private final IDateTimeExtractor dateExtractor;
    private final IDateTimeExtractor timeExtractor;
    private final IDateTimeParser dateParser;
    private final IDateTimeParser timeParser;
    private final IExtractor cardinalExtractor;
    private final IExtractor integerExtractor;
    private final IParser numberParser;
    private final IDateTimeExtractor durationExtractor;
    private final IDateTimeParser durationParser;

    private final Pattern nowRegex;
    private final Pattern amTimeRegex;
    private final Pattern pmTimeRegex;
    private final Pattern simpleTimeOfTodayAfterRegex;
    private final Pattern simpleTimeOfTodayBeforeRegex;
    private final Pattern specificTimeOfDayRegex;
    private final Pattern specificEndOfRegex;
    private final Pattern unspecificEndOfRegex;
    private final Pattern unitRegex;
    private final Pattern dateNumberConnectorRegex;
    private final Pattern yearRegex;

    private final ImmutableMap<String, String> unitMap;
    private final ImmutableMap<String, Integer> numbers;
    private final IDateTimeUtilityConfiguration utilityConfiguration;

    public GermanDateTimeParserConfiguration(ICommonDateTimeParserConfiguration config) {

        super(config.getOptions());

        tokenBeforeDate = GermanDateTime.TokenBeforeDate;
        tokenBeforeTime = GermanDateTime.TokenBeforeTime;
        
        cardinalExtractor = config.getCardinalExtractor();
        integerExtractor = config.getIntegerExtractor();
        numberParser = config.getNumberParser();
        dateExtractor = config.getDateExtractor();
        timeExtractor = config.getTimeExtractor();
        durationExtractor = config.getDurationExtractor();
        dateParser = config.getDateParser();
        timeParser = config.getTimeParser();
        durationParser = config.getDurationParser();
        
        nowRegex = GermanDateTimeExtractorConfiguration.NowRegex;

        amTimeRegex = RegExpUtility.getSafeRegExp(GermanDateTime.AMTimeRegex);
        pmTimeRegex = RegExpUtility.getSafeRegExp(GermanDateTime.PMTimeRegex);

        simpleTimeOfTodayAfterRegex = GermanDateTimeExtractorConfiguration.SimpleTimeOfTodayAfterRegex;
        simpleTimeOfTodayBeforeRegex = GermanDateTimeExtractorConfiguration.SimpleTimeOfTodayBeforeRegex;
        specificTimeOfDayRegex = GermanDateTimeExtractorConfiguration.SpecificTimeOfDayRegex;
        specificEndOfRegex = GermanDateTimeExtractorConfiguration.SpecificEndOfRegex;
        unspecificEndOfRegex = GermanDateTimeExtractorConfiguration.UnspecificEndOfRegex;
        unitRegex = GermanTimeExtractorConfiguration.TimeUnitRegex;
        dateNumberConnectorRegex = GermanDateTimeExtractorConfiguration.DateNumberConnectorRegex;
        yearRegex = GermanDateTimeExtractorConfiguration.YearRegex;

        unitMap = config.getUnitMap();
        numbers = config.getNumbers();
        utilityConfiguration = config.getUtilityConfiguration();
    }

    @Override
    public String getTokenBeforeDate() {
        return tokenBeforeDate;
    }

    @Override
    public String getTokenBeforeTime() {
        return tokenBeforeTime;
    }

    @Override
    public IDateTimeExtractor getDateExtractor() {
        return dateExtractor;
    }

    @Override
    public IDateTimeExtractor getTimeExtractor() {
        return timeExtractor;
    }

    @Override
    public IDateTimeParser getDateParser() {
        return dateParser;
    }

    @Override
    public IDateTimeParser getTimeParser() {
        return timeParser;
    }

    @Override
    public IExtractor getCardinalExtractor() {
        return cardinalExtractor;
    }

    @Override
    public IExtractor getIntegerExtractor() {
        return integerExtractor;
    }

    @Override
    public IParser getNumberParser() {
        return numberParser;
    }

    @Override
    public IDateTimeExtractor getDurationExtractor() {
        return durationExtractor;
    }

    @Override
    public IDateTimeParser getDurationParser() {
        return durationParser;
    }

    @Override
    public Pattern getNowRegex() {
        return nowRegex;
    }

    @Override
    public Pattern getAMTimeRegex() {
        return amTimeRegex;
    }

    @Override
    public Pattern getPMTimeRegex() {
        return pmTimeRegex;
    }

    @Override
    public Pattern getSimpleTimeOfTodayAfterRegex() {
        return simpleTimeOfTodayAfterRegex;
    }

    @Override
    public Pattern getSimpleTimeOfTodayBeforeRegex() {
        return simpleTimeOfTodayBeforeRegex;
    }

    @Override
    public Pattern getSpecificTimeOfDayRegex() {
        return specificTimeOfDayRegex;
    }

    @Override
    public Pattern getSpecificEndOfRegex() {
        return specificEndOfRegex;
    }

    @Override
    public Pattern getUnspecificEndOfRegex() {
        return unspecificEndOfRegex;
    }

    @Override
    public Pattern getUnitRegex() {
        return unitRegex;
    }

    @Override
    public Pattern getDateNumberConnectorRegex() {
        return dateNumberConnectorRegex;
    }

    @Override
    public ImmutableMap<String, String> getUnitMap() {
        return unitMap;
    }

    @Override
    public ImmutableMap<String, Integer> getNumbers() {
        return numbers;
    }

    @Override
    public IDateTimeUtilityConfiguration getUtilityConfiguration() {
        return utilityConfiguration;
    }

    @Override
    public boolean containsAmbiguousToken(String text, String matchedText) {
        return false;
    }

    @Override
    public ResultTimex getMatchedNowTimex(String text) {

        String trimmedText = text.trim().toLowerCase();
        
        if (trimmedText.endsWith("jetzt") ||
                trimmedText.equals("momentan") ||
                trimmedText.equals("gerade") ||
                trimmedText.equals("aktuell") ||
                trimmedText.equals("aktuelle") ||
                trimmedText.equals("im moment") ||
                trimmedText.equals("in diesem moment") ||
                trimmedText.equals("derzeit")) {
            return new ResultTimex(true, "PRESENT_REF");
        } else if (trimmedText.equals("neulich") ||
                trimmedText.equals("vorher") ||
                trimmedText.equals("vorhin")) {
            return new ResultTimex(true, "PAST_REF");
        } else if (trimmedText.equals("so früh wie möglich")) {
            return new ResultTimex(true, "FUTURE_REF");
        }

        return new ResultTimex(false, null);
    }

    @Override
    public int getSwiftDay(String text) {

        String trimmedText = text.trim().toLowerCase();

        int swift = 0;
        if (trimmedText.startsWith("nächsten") ||
                trimmedText.startsWith("nächste") ||
                trimmedText.startsWith("nächstes") ||
                trimmedText.startsWith("nächster")) {
            swift = 1;
        } else if (trimmedText.startsWith("letzten") ||
                trimmedText.startsWith("letzte") ||
                trimmedText.startsWith("letztes") ||
                trimmedText.startsWith("letzter")) {
            swift = -1;
        }

        return swift;
    }

    @Override
    public int getHour(String text, int hour) {

        String trimmedText = text.trim().toLowerCase();
        int result = hour;
        
        if ((trimmedText.endsWith("morgen") || trimmedText.endsWith("morgens"))
                && hour >= Constants.HalfDayHourCount) {
            result -= Constants.HalfDayHourCount;
        } else if (!(trimmedText.endsWith("morgen") || trimmedText.endsWith("morgens"))
                && hour < Constants.HalfDayHourCount) {
            result += Constants.HalfDayHourCount;
        }
        
        return result;
    }

    public Pattern getYearRegex() {
        return yearRegex;
    }
}
