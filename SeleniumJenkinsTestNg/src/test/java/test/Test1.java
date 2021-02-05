package test;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import base.BaseTests;
import pages.HomePage;

public class Test1 extends BaseTests {
	public static HomePage homePage;

	@Test(priority = 1)
	public void test1() {
		googlePage.sendKeyWord("Test1");
		homePage = googlePage.clickSearchBtn();
		String title = homePage.getTitle();
		Assert.assertTrue(title.contains("Test0"), "the title contains not Test0");
	}

	@Test(priority = 2)
	public void test2() {
		googlePage.sendKeyWord("Test2");
		homePage = googlePage.clickSearchBtn();
		String title = homePage.getTitle();
		Assert.assertTrue(title.contains("Test2"), "the title contains not Test4");

	}

	@Test(priority = 3)
	public void test3() {
		googlePage.sendKeyWord("Test3");
		homePage = googlePage.clickSearchBtn();
		throw new SkipException("test-case skipped");

	}

	@Test(priority = 4)
	public void test4() {
		googlePage.sendKeyWord("Test4");
		homePage = googlePage.clickSearchBtn();
		String title = homePage.getTitle();
		System.out.println("the title is :" + title);
		Assert.assertTrue(title.contains("Test4"), "the title contains not Test4");

	}
}
