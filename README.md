# Payworks task
Payworks task: *​Credit​ ​Card​ ​Number​ ​Evaluator*

This task melded well with my experience of card management in the POS industry.

## Aims
In this code I have attempted to demonstrate my proficiency in the following areas.

#### Credit card management

My many years of experience dealing with credit cards has highlighted a number of important requirements for retailers.

1. The ability to add extra card ranges to the validation system to allow for special cards such as loyalty cards.
2. Validation of other range-based inputs such as voucher barcodes.

Although this is obviously only two day work I believe I have achieved most of the functionality I planned to implement.

#### Data structures

I took the opportunity here to make use of an Interval Tree. This structure works perfectly for fast lookups of PANS to discover the correct scheme.
 
## Code walk-through

The task was to validate and classify a short list of PANS. The correctness of my solution can be seen in the `CardFactoryTest`class where each of the supplied PANs are clearly validated and matched with their schemes correctly.
 
 

