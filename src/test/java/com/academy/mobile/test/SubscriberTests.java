package com.academy.mobile.test;

import com.academy.mobile.manager.model.SubscriberData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SubscriberTests {

    @BeforeMethod
    public void prepare() {
        //manager.goTo().subscriber();
    }

    @Test(dataProvider = "creationSubscriber")
    public void testAddSubscriberUI(SubscriberData subscriber) {
//        if (manager.subscriber().isPresentId(subscriber.getId())) {
//            manager.subscriber().remove(subscriber.getId());
//        }
//
//        // ui
//        Subscribers beforeUI = manager.ui().subscriber().all();
//        //Subscribers beforeBD = manager.bd().subscriber().all();
//        //Subscribers beforeHTTP = manager.http().subscriber().all();
//
//        assertThat(beforeUI.size(), equalTo(beforeBD.size()), equalTo(beforeHTTP.size()));
//
//        manager.ui().subscriber().create(subscriber);
//
//        // verify
//        assertThat(manager.subscriber().count(), equalTo(before.size()+1));
//        Subscribers afterUI = manager.ui().subscriber().all();
//
//        assertThat(afterUI.size(), equalTo(afterBD.size()), equalTo(afterHTTP.size()));
//
//        // bd
//        //if (needCheck())
//        //assertThat(afterBD, equalTo(beforeBD.withAdded(subscriber)));
//
//        // http
//        //if (needCheck())
//        //assertThat(afterHTTP, equalTo(beforeHTTP.withAdded(subscriber)));
//
//        // ui
//        if (needCheck())
//            assertThat(afterUI, equalTo(beforeUI.withAdded(subscriber)));
    }

    @DataProvider(name="creationSubscriber")
    private Object[] getCreationSubscriberData() {
        return new Object[]{
//                new SubscriberData()
//                        .withFirstName("Kolya")
//                        .withLastName("Ivanov")
//                        .withAddress("Petrovskogo st. 35")
//                        .withCity("Kharkov")
//                        .withState("Alaska")
//                        .withZipCode("61033")
//                        .withCountry("United States")
//                        .withHomePhone("+3809353613437")
//                        .withMobilePhone("093234567")
//                        .withAddressAlias("addressRef")
        };
    }
}
