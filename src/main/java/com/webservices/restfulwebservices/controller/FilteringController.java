package com.webservices.restfulwebservices.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.webservices.restfulwebservices.model.SomeBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {


    //@JsonIgnoreProperties("field1")   in SomeBean  static filtering
    /*  @JsonIgnore    in SomeBean  static filtering*/
    @GetMapping("/filtering")
    public SomeBean filteringStatic() {
        return new SomeBean("value1", "value2", "value3");
    }
    //@JsonIgnoreProperties("field1")   in SomeBean  static filtering
    /*  @JsonIgnore    in SomeBean  static filtering*/
    @GetMapping("/filtering-list")
    public List<SomeBean> filteringList() {
        return Arrays.asList(new SomeBean("1", "2", "3")
                , new SomeBean("4", "5", "6"));
    }


  //Dynamic filter in SomeBean should be add @JsonFilter("SomeBeanFilter")
    @GetMapping("/filtering-dyn")
    public MappingJacksonValue filteringDynamic() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        // just field1,field2 in response
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list-dyn")
    public MappingJacksonValue filteringListDynamic() {
        List<SomeBean> list = Arrays.asList(new SomeBean("1", "2", "3")
                , new SomeBean("4", "5", "6"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        // just field1,field2 in response
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }


}
