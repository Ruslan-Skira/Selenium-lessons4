package com.academy.automation.practice.ddt.test;

import com.academy.automation.practice.ddt.manager.model.AddressData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AddressTest extends BaseTest {

    @Test
    // TODO
    public void testAddAddress() {
        AddressData addressData = new AddressData()
                .withFirstName("Kolya")
                .withLastName("Ivanov")
                .withAddress("Petrovskogo st. 35")
                .withCity("Kharkov")
                .withState("Alaska")
                .withZipCode("61033")
                .withCountry("United States")
                .withHomePhone("+3809353613437")
                .withMobilePhone("093234567")
                .withAddressAlias("addressRef");

        manager.goTo().home();
        manager.session().login();
        manager.goTo().address();
//        if (manager.address().isPresentAlias(addressData.getAddressAlias())) {
//            manager.address().removeAddress(addressData.getAddressAlias());
//        }
//
//        int before = manager.address().getCountAddresses();
//        List<AddressData> beforeListAddr = manager.address().getAddresses();
//
//        manager.address().initCreationAddress();
//        manager.address().fillAddressForm(addressData);
//        manager.address().submitAddress();
//
//        // verify
//        int after = manager.address().getCountAddresses();
//        List<AddressData> afterListAddr = manager.address().getAddresses();
//        Assert.assertEquals(afterListAddr.size(), beforeListAddr.size()+1);
//        beforeListAddr.add(addressData.withAddressAlias(addressData.getAddressAlias().toUpperCase()));
//        Assert.assertEquals(beforeListAddr, afterListAddr);
    }
}
