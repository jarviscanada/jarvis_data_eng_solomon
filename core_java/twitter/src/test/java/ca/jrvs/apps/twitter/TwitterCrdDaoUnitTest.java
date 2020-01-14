package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Entities;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TwitterCrdDaoUnitTest {
  Tweet realTweet;
  @Mock
  TwitterHttpHelper twitterHttpHelper;
  @InjectMocks
  TwitterCrdDao twitterCrdDao;
  
  @Before
  public void setUp() {
    twitterCrdDao = new TwitterCrdDao(twitterHttpHelper);
    realTweet = new Tweet(
        new Date(),
        1208114830516850688L,
        "1208114830516850688",
        "this is the text body of a tweet",
        new Entities(null, null, null, null),
        new Coordinates(),
        0,
        0,
        false,
        false
    );
  }
  
  @After
  public void tearDown() throws Exception {
    twitterCrdDao = null;
  }

  @Test
  public void aFindById() throws Exception {
    TwitterCrdDao spyDao = spy(twitterCrdDao);
    doReturn(realTweet).when(spyDao).createObjectFromHttpResponse(any(), any());
    Tweet foundTweet = spyDao.findById(realTweet.getIdString());
    
    assertNotNull(foundTweet);
    assertTrue(foundTweet.getCreatedAt().getTime() < System.currentTimeMillis());
  }
  
  @Test
  public void bCreate() throws Exception {
    TwitterCrdDao spyDao = spy(twitterCrdDao);
    
    doReturn(new Tweet()).when(spyDao).createObjectFromHttpResponse(any(), any());
    Tweet foundTweet = spyDao.findById(realTweet.getIdString());
  
    foundTweet.setText(realTweet.getText() + " " + System.currentTimeMillis());
  
    doReturn(foundTweet).when(spyDao).create(any());
    Tweet createdTweet = spyDao.create(foundTweet);
    
    assertNotNull(createdTweet);
    assertNotEquals(createdTweet.getText(), realTweet.getText());
  }
  
  @Test(expected = RuntimeException.class)
  public void cDeleteById() throws Exception {
    TwitterCrdDao spyDao = spy(twitterCrdDao);
    doReturn(realTweet).when(spyDao).createObjectFromHttpResponse(any(), any());
    Tweet deletedTweet = spyDao.deleteById(realTweet.getIdString());
    
    when(spyDao.findById(deletedTweet.getIdString())).thenThrow(new RuntimeException("Mock: Tweet " +
                                                                                      "cannot be " +
                                                                                      "found"));
    deletedTweet = spyDao.findById(deletedTweet.getIdString());
  }
}