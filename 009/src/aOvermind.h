/*
 * aOvermind.h
 *
 *  Created on: Jun 18, 2014
 *      Author: pilot
 */

#ifndef AOVERMIND_H_
#define AOVERMIND_H_

/* TODO: the aOvermind abstract class should contain the prototypes for the
 * calls to start up the necessary services, a thread to monitor their health,
 * and error handling logic for anything that percolates all the way back up
 * the call stack */

#include "aZerg.h"
#include "aBrood.h"

class aStockList {};
class aInfoList {};
class aInfoRequest {};
class aMarketOrder {};

class aCerebrate {};
class aPriceDownloadCerebrate {};
class aPriceSaveCerebrate {};
class aPriceDiskReadCerebrate {};
class aPriceActionAnalyzerCerebrate {};
class aOrderMakerCerebrate {};
class aResponsibleCerebrate {};

class aOvermind
{
	public:
		int main() {};

	private:
		// container for each of the Cerebrates
//		aBrood<aCerebrate> theSwarm;

		//	abstract class to manage the objects which download stock prices
		aPriceDownloadCerebrate getterCerebrate;
		//	abstract class to manage the objects which record stock prices
		//		both to disk and to the database
		aPriceSaveCerebrate writerCerebrate;
		//	abstract class to manage the objects which retrieve stock prices
		//		from disk and populate the database
		aPriceDiskReadCerebrate readerCerebrate;
		//	abstract class to manage the objects which analyze the stock
		//		data in the database
		aPriceActionAnalyzerCerebrate analyzerCerebrate;
		//	abstract class to managed the objects that place orders with the
		//		broker
		aOrderMakerCerebrate orderCerebrate;
		//	abstract class to decide whether and what order to make based
		//		on the results of the analyzerCerebrate
		aResponsibleCerebrate responsibleCerebrate;
};

#endif /* AOVERMIND_H_ */
