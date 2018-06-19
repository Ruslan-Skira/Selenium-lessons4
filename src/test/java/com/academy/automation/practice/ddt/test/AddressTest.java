package com.academy.automation.practice.ddt.test;

import com.academy.automation.practice.ddt.manager.model.AddressData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AddressTest extends BaseTest {

    @BeforeMethod
    public void prepare() {
        manager.goTo().home();
        manager.session().login();
        manager.goTo().address();
    }

    @Test(dataProvider = "creationAddress")
    public void testAddAddress(AddressData address) {
        if (manager.address().isPresentAlias(address.getAlias())) {
            manager.address().removeAddress(address.getAlias());
        }

        List<AddressData> beforeListAddr = manager.address().getAddresses();
        manager.address().create(address);

        // verify
        List<AddressData> afterListAddr = manager.address().getAddresses();
        Assert.assertEquals(afterListAddr.size(), beforeListAddr.size()+1);
        beforeListAddr.add(address.withAddressAlias(address.getAlias().toUpperCase()));
        Assert.assertEquals(new HashSet<>(beforeListAddr), new HashSet<>(afterListAddr));
    }

    @DataProvider(name="creationAddress")
    private Object[] getCreationAddressData() {
        return new Object[]{
                new AddressData()
                        .withFirstName("Kolya")
                        .withLastName("Ivanov")
                        .withAddress("Petrovskogo st. 35")
                        .withCity("Kharkov")
                        .withState("Alaska")
                        .withZipCode("61033")
                        .withCountry("United States")
                        .withHomePhone("+3809353613437")
                        .withMobilePhone("093234567")
                        .withAddressAlias("addressRef")
        };
    }
}
