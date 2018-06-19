package com.academy.automation.practice.ddt.test;

import com.academy.automation.practice.ddt.manager.model.AddressData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Set;

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
            manager.address().remove(address.getAlias());
        }

        Set<AddressData> before = manager.address().all();
        manager.address().create(address);

        // verify
        Set<AddressData> after = manager.address().all();
        Assert.assertEquals(after.size(), before.size()+1);
        before.add(address.withAddressAlias(address.getAlias().toUpperCase()));
        Assert.assertEquals(after, before);
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
