/**
 * Copyright (c) 2017 Larry Ricker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.larryricker;

import java.io.IOException;
import java.net.MalformedURLException;

import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriverException;

@Tag("ios")
public class TestSettingsTab {

	private static final Logger LOGGER = Logger.getLogger(TestSettingsTab.class.getName());

	@BeforeAll
	public static void setupBeforeClass() {

	}

	@AfterAll
	public static void tearDownAfterClass() throws MalformedURLException {
	}

	@BeforeEach
	public void setup(String capabilities, TestInfo info, TestReporter testReporter) throws MalformedURLException {
	}

	@AfterEach
	public void cleanup(String capabilities, TestInfo info, TestReporter testReporter) throws MalformedURLException {
		Driver.destroy();
	}

	@ParameterizedTest
	@ValueSource(strings= {"BadWithNames.json"
	, "BadWithNamesPro.json", "GoodWithNames.json"
	,"GoodWithNamesPro.json", "PMIS-Pro.json"
	, "PMIS.json", "ProgressReport.json"
	, "ProgressReportPro.json", "ProjectInfo.json"
	, "ProjectInfoPro.json", "ProjectOrganizer.json"
	, "ProjectOrganizerPro.json", "StatusReport4.json"
	, "StatusReport4Pro.json"})
	@DisplayName("Settings Tab")
	public void testSettingsTab(String capabilities, TestInfo info, TestReporter testReporter) throws WebDriverException, IOException {
		Assert.assertNotNull("capabilities is not null", capabilities);
		testReporter.publishEntry(info.getDisplayName(), "Capabilities under test "+ capabilities);
		System.setProperty("TEST_CAPABILITIES", capabilities);
		// identify current app
		String bundleId = Org.getBundleId(testReporter);
		Org.gotoSettingsTableView();
		// verify upsell message
		Org.verifySettingsUpsellMessage();
		// click on upsell and buy app - skip for now
		// Click on rate our app
		// App.click(" Rate our app"); // not testable at this time
		// exit app rating
		Org.shareAppFromSettingsTableView();
		// restore purchases - skip for now
		Org.exitSettingsTableView();
	}

}
