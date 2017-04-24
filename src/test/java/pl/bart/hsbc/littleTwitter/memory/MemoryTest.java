package pl.bart.hsbc.littleTwitter.memory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import pl.bart.hsbc.littleTwitter.entity.Message;

public class MemoryTest {

	private static final int MIN_USERNAME_LENGTH = 10;
	private static final int MAX_USERNAME_LENGTH = 32;
	private static final int TYPICAL_TWIT_LENGTH = 100;
	private static final int TOO_LONG_TWIT_LENGTH = 200;

	Memory objectUnderTest = Memory.getInstance();

	@Test
	public void testCorrectAddTwitAndCheckIfExists() {
		String username = getUsername();
		String message = getMessage(TYPICAL_TWIT_LENGTH);
		boolean result = objectUnderTest.addMessage(username, message);
		List<Message> messageList = objectUnderTest.getWall(username);
		assertTrue(result);
		assertEquals(messageList.size(), 1);
		assertEquals(messageList.get(0).getUser().getName(), username);
		assertEquals(messageList.get(0).getText(), message);
	}

	@Test
	public void testAddTooLongTwitAndCheckIfExist() {
		String username = getUsername();
		String message = getMessage(TOO_LONG_TWIT_LENGTH);
		boolean result = objectUnderTest.addMessage(username, message);
		List<Message> messageList = objectUnderTest.getWall(username);
		assertFalse(result);
		assertTrue(messageList.isEmpty());
	}

	@Test
	public void testAddEmptyTwitAndCheckIfExist() {
		String username = getUsername();
		String message = StringUtils.EMPTY;
		boolean result = objectUnderTest.addMessage(username, message);
		List<Message> messageList = objectUnderTest.getWall(username);
		assertFalse(result);
		assertTrue(messageList.isEmpty());
	}

	@Test
	public void testAddTwoTwitsAndCheckIfTheyAreInOrder() {
		String username = getUsername();
		objectUnderTest.addMessage(username, getMessage(TYPICAL_TWIT_LENGTH));
		objectUnderTest.addMessage(username, getMessage(TYPICAL_TWIT_LENGTH));
		List<Message> messageList = objectUnderTest.getWall(username);
		assertEquals(messageList.size(), 2);
		assertTrue(validateDescendingOrder(messageList));
	}

	@Test
	public void testFollowTwoExistingUsersAndCheckIfAreAdded() {
		String username = getUsername();
		String follow1 = getUsername();
		String follow2 = getUsername();
		addUsersByOneTwit(username, follow1, follow2);
		boolean result1 = objectUnderTest.follow(username, follow1);
		boolean result2 = objectUnderTest.follow(username, follow2);
		Set<String> follows = objectUnderTest.checkFollow(username);
		assertTrue(result1);
		assertTrue(result2);
		assertEquals(follows.size(), 2);
		assertTrue(follows.contains(follow1));
		assertTrue(follows.contains(follow2));
	}

	@Test
	public void testFollowNonExistingUser() {
		String username = getUsername();
		String follow = getUsername();
		addUsersByOneTwit(username);
		boolean result = objectUnderTest.follow(username, follow);
		Set<String> follows = objectUnderTest.checkFollow(username);
		assertFalse(result);
		assertTrue(follows.isEmpty());
	}

	@Test
	public void testFollowItself() {
		String username = getUsername();
		addUsersByOneTwit(username);
		boolean result = objectUnderTest.follow(username, username);
		Set<String> follows = objectUnderTest.checkFollow(username);
		assertFalse(result);
		assertTrue(follows.isEmpty());
	}

	@Test
	public void testFollowAndUnfollowUser() {
		String username = getUsername();
		String follow = getUsername();
		addUsersByOneTwit(username, follow);
		boolean resultFollow = objectUnderTest.follow(username, follow);
		Set<String> followsBefore = objectUnderTest.checkFollow(username);
		assertTrue(resultFollow);
		assertEquals(followsBefore.size(), 1);
		boolean resultUnfollow = objectUnderTest.unfollow(username, follow);
		Set<String> followsAfter = objectUnderTest.checkFollow(username);
		assertTrue(resultUnfollow);
		assertTrue(followsAfter.isEmpty());
	}

	@Test
	public void testUnfollowNoFollowingUser() {
		String username = getUsername();
		String follow = getUsername();
		addUsersByOneTwit(username, follow);
		boolean result = objectUnderTest.unfollow(username, follow);
		assertFalse(result);
	}

	@Test
	public void testFollowTwoUsersAndCheckMessagesInOrder() {
		String username = getUsername();
		String follow1 = getUsername();
		String follow2 = getUsername();
		addUsersByOneTwit(username, follow1, follow2);
		addUsersByOneTwit(follow2, follow1);
		objectUnderTest.follow(username, follow1);
		objectUnderTest.follow(username, follow2);
		List<Message> messageList = objectUnderTest.getTimeLine(username);
		assertEquals(messageList.size(), 4);
		assertTrue(validateDescendingOrder(messageList));
	}

	private String getUsername() {
		return RandomStringUtils.randomAlphabetic(MIN_USERNAME_LENGTH, MAX_USERNAME_LENGTH);
	}

	private String getMessage(int length) {
		return RandomStringUtils.random(length);
	}

	private void addUsersByOneTwit(String... users) {
		for (String user : users) {
			objectUnderTest.addMessage(user, getMessage(TYPICAL_TWIT_LENGTH));
		}
	}

	private boolean validateDescendingOrder(List<Message> list) {
		return list.stream().sorted((msg1, msg2) -> msg2.getCreationTime().compareTo(msg1.getCreationTime()))
				.collect(Collectors.toList()).equals(list);
	}

}
