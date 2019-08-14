package com.subhadeep.rest.webservices.restfulwebservices.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.subhadeep.rest.webservices.restfulwebservices.model.SomeBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

//    Dynamic Filtering= I only want to send field1 and field2 and I dont want field3
    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean() {
        SomeBean someBean = new SomeBean("Value1", "Value2", "Value3");

//        Instantiate SimpleBeanPropertyFilter to mention what attributes of the bean
//        needs to be ignored. Below we have done it by mentioning attributes with name
//        "field1", "field2" which will only be accepted
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");

//        Configure a filter by passing the SimpleBeanPropertyFilter to ProvidedFilter class
//        In this step we are actually configuring a filter.
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

//        Using MappingJacksonValue class to set the configured filter to our
//        bean "someBean"
        MappingJacksonValue mapping  = new MappingJacksonValue(someBean);
        mapping.setFilters(filters);

//   ***  Another important thing that most developers forget is to add the filter
//   ***  name that was configured above to the bean class on which filtering will
//   ***  be done

//      return mapping instead of the actual bean
        return mapping;
    }

//    Dynamic filtering= over here we would only wasnt to send field2 and field3
    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListOfSomeBeans() {
        List<SomeBean> someBeans = Arrays.asList(
                new SomeBean("Value1", "Value2", "Value3"),
                new SomeBean("val1", "val2", "val3"),
                new SomeBean("1", "2", "3"));
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mapping  = new MappingJacksonValue(someBeans);
        mapping.setFilters(filters);
        return mapping;
    }

    @PostMapping("/filtering")
    public SomeBean allSomeBean(@RequestBody SomeBean someBean) {
//        someService.save(someBean)
        return someBean;
    }

}
