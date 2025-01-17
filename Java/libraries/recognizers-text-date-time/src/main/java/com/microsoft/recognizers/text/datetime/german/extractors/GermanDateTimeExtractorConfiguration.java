// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.recognizers.text.datetime.german.extractors;

import com.microsoft.recognizers.text.IExtractor;
import com.microsoft.recognizers.text.datetime.DateTimeOptions;
import com.microsoft.recognizers.text.datetime.config.BaseOptionsConfiguration;
import com.microsoft.recognizers.text.datetime.extractors.BaseDateExtractor;
import com.microsoft.recognizers.text.datetime.extractors.BaseDurationExtractor;
import com.microsoft.recognizers.text.datetime.extractors.BaseTimeExtractor;
import com.microsoft.recognizers.text.datetime.extractors.IDateTimeExtractor;
import com.microsoft.recognizers.text.datetime.extractors.config.IDateTimeExtractorConfiguration;
import com.microsoft.recognizers.text.datetime.german.utilities.GermanDatetimeUtilityConfiguration;
import com.microsoft.recognizers.text.datetime.resources.GermanDateTime;
import com.microsoft.recognizers.text.datetime.utilities.IDateTimeUtilityConfiguration;
import com.microsoft.recognizers.text.number.german.extractors.IntegerExtractor;
import com.microsoft.recognizers.text.utilities.RegExpUtility;
import com.microsoft.recognizers.text.utilities.StringUtility;

import java.util.Arrays;
import java.util.regex.Pattern;

public class GermanDateTimeExtractorConfiguration extends BaseOptionsConfiguration implements IDateTimeExtractorConfiguration {

    public static final Pattern PrepositionRegex = RegExpUtility.getSafeRegExp(GermanDateTime.PrepositionRegex);
    public static final Pattern NowRegex = RegExpUtility.getSafeRegExp(GermanDateTime.NowRegex);
    public static final Pattern SuffixRegex = RegExpUtility.getSafeRegExp(GermanDateTime.SuffixRegex);
    public static final Pattern TimeOfDayRegex = RegExpUtility.getSafeRegExp(GermanDateTime.TimeOfDayRegex);
    public static final Pattern SpecificTimeOfDayRegex = RegExpUtility.getSafeRegExp(GermanDateTime.SpecificTimeOfDayRegex);
    public static final Pattern TimeOfTodayAfterRegex = RegExpUtility.getSafeRegExp(GermanDateTime.TimeOfTodayAfterRegex);
    public static final Pattern TimeOfTodayBeforeRegex = RegExpUtility.getSafeRegExp(GermanDateTime.TimeOfTodayBeforeRegex);
    public static final Pattern SimpleTimeOfTodayAfterRegex = RegExpUtility.getSafeRegExp(GermanDateTime.SimpleTimeOfTodayAfterRegex);
    public static final Pattern SimpleTimeOfTodayBeforeRegex = RegExpUtility.getSafeRegExp(GermanDateTime.SimpleTimeOfTodayBeforeRegex);
    public static final Pattern SpecificEndOfRegex = RegExpUtility.getSafeRegExp(GermanDateTime.SpecificEndOfRegex);
    public static final Pattern UnspecificEndOfRegex = RegExpUtility.getSafeRegExp(GermanDateTime.UnspecificEndOfRegex);
    public static final Pattern UnitRegex = RegExpUtility.getSafeRegExp(GermanDateTime.TimeUnitRegex);
    public static final Pattern ConnectorRegex = RegExpUtility.getSafeRegExp(GermanDateTime.ConnectorRegex);
    public static final Pattern NumberAsTimeRegex = RegExpUtility.getSafeRegExp(GermanDateTime.NumberAsTimeRegex);
    public static final Pattern DateNumberConnectorRegex = RegExpUtility.getSafeRegExp(GermanDateTime.DateNumberConnectorRegex);
    public static final Pattern YearSuffix = RegExpUtility.getSafeRegExp(GermanDateTime.YearSuffix);
    public static final Pattern YearRegex = RegExpUtility.getSafeRegExp(GermanDateTime.YearRegex);
    public static final Pattern SuffixAfterRegex = RegExpUtility.getSafeRegExp(GermanDateTime.SuffixAfterRegex);

    public IExtractor integerExtractor;
    public IDateTimeExtractor datePointExtractor;
    public IDateTimeExtractor timePointExtractor;
    public IDateTimeExtractor durationExtractor;
    public IDateTimeUtilityConfiguration utilityConfiguration;

    public GermanDateTimeExtractorConfiguration(DateTimeOptions options) {

        super(options);

        integerExtractor = new IntegerExtractor();
        datePointExtractor = new BaseDateExtractor(new GermanDateExtractorConfiguration(this));
        timePointExtractor = new BaseTimeExtractor(new GermanTimeExtractorConfiguration(options));
        durationExtractor = new BaseDurationExtractor(new GermanDurationExtractorConfiguration(options));

        utilityConfiguration = new GermanDatetimeUtilityConfiguration();
    }

    public GermanDateTimeExtractorConfiguration() {
        this(DateTimeOptions.None);
    }

    @Override
    public Pattern getNowRegex() {
        return NowRegex;
    }

    @Override
    public Pattern getSuffixRegex() {
        return SuffixRegex;
    }

    @Override
    public Pattern getTimeOfTodayAfterRegex() {
        return TimeOfTodayAfterRegex;
    }

    @Override
    public Pattern getSimpleTimeOfTodayAfterRegex() {
        return SimpleTimeOfTodayAfterRegex;
    }

    @Override
    public Pattern getTimeOfTodayBeforeRegex() {
        return TimeOfTodayBeforeRegex;
    }

    @Override
    public Pattern getSimpleTimeOfTodayBeforeRegex() {
        return SimpleTimeOfTodayBeforeRegex;
    }

    @Override
    public Pattern getTimeOfDayRegex() {
        return TimeOfDayRegex;
    }

    @Override
    public Pattern getSpecificEndOfRegex() {
        return SpecificEndOfRegex;
    }

    @Override
    public Pattern getUnspecificEndOfRegex() {
        return UnspecificEndOfRegex;
    }

    @Override
    public Pattern getUnitRegex() {
        return UnitRegex;
    }

    @Override
    public Pattern getNumberAsTimeRegex() {
        return NumberAsTimeRegex;
    }

    @Override
    public Pattern getDateNumberConnectorRegex() {
        return DateNumberConnectorRegex;
    }

    public Pattern getYearSuffix() {
        return YearSuffix;
    }

    public Pattern getYearRegex() {
        return YearRegex;
    }

    @Override
    public Pattern getSuffixAfterRegex() {
        return SuffixAfterRegex;
    }

    @Override
    public IDateTimeExtractor getDurationExtractor() {
        return durationExtractor;
    }

    @Override
    public IDateTimeExtractor getDatePointExtractor() {
        return datePointExtractor;
    }

    @Override
    public IDateTimeExtractor getTimePointExtractor() {
        return timePointExtractor;
    }

    @Override
    public IExtractor getIntegerExtractor() {
        return integerExtractor;
    }

    @Override
    public IDateTimeUtilityConfiguration getUtilityConfiguration() {
        return utilityConfiguration;
    }

    public boolean isConnector(String text) {

        text = text.trim();

        boolean isPreposition = Arrays.stream(RegExpUtility.getMatches(PrepositionRegex, text)).findFirst().isPresent();
        boolean isConnector = Arrays.stream(RegExpUtility.getMatches(ConnectorRegex, text)).findFirst().isPresent();
        return (StringUtility.isNullOrEmpty(text) || isPreposition || isConnector);
    }
}
