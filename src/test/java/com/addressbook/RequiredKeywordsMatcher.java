package com.addressbook;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RequiredKeywordsMatcher implements Matcher<String> {

    final List<String> keywords;

    public RequiredKeywordsMatcher(String ... keywords) {
        this.keywords = Arrays.stream(keywords).map(String::toLowerCase).collect(Collectors.toList());
    }

    @Override
    public boolean matches(Object o) {
        if(o == null){
            return false;
        }
        for (String keyword : keywords) {
            if(!((String) o).toLowerCase().contains(keyword)) return false;
        }
        return true;
    }

    @Override
    public void describeMismatch(Object o, Description description) {

    }

    @Override
    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {

    }

    @Override
    public void describeTo(Description description) {
        description.appendValueList("containing keywords "," ", "",keywords);
    }
}
