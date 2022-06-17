package unit.service;

import com.revature.model.Offer;
import com.revature.service.OfferService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class OfferServiceUnitTest {
    List<Offer> mockList= Mockito.mock(List.class);

    @Test
    public void whenGivenOfferObjectCreateNewObjectAddsToList(){
        Offer offer = new Offer();
        OfferService offerService=new OfferService();

        Assertions.assertDoesNotThrow(()->offerService.createOffer(offer));
    }

    //@Test
    //public void whenGivenOfferObjectCreateNewObjectReturnTrue(){
    //    Offer offer = new Offer();
    //    OfferService offerService=new OfferService(mockList);
    //    Mockito.when(mockList.add(offer)).thenReturn(true);
    //
    //    boolean result1=offerService.createOffer(offer);
    //    Assertions.assertTrue(result1);
    //}

    @Test
    public void whenGetAllOffersIsCalledReturnsListOfOffers(){
        OfferService offerService=new OfferService();
        Assertions.assertDoesNotThrow(()->offerService.getAllOffers());
    }

    //@Test
    //public void whenGivenValidIdGetOfferByIdReturnsOfferWithThatId(){
    //    Offer offer = new Offer(0, 0, 100.00);

    //    Mockito.when(mockList.size()).thenReturn(1);
    //    Mockito.when(mockList.get(0)).thenReturn(offer);
    //    OfferService offerService=new OfferService(mockList);
    //    Offer result = offerService.getOfferById(0);

    //    Assertions.assertEquals(offer, result);
    //}
}
