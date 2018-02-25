# Payworks task
Payworks task: *​Credit​ ​Card​ ​Number​ ​Evaluator*

This task melded well with my experience of card management in the POS industry. I also managed to show off a little by using an interval tree for the range lookup.

#### Aims
In this code I have attempted to demonstrate my proficiency in the following areas.

##### Credit card management

My many years of experience dealing with credit cards has highlighted a number of important requirements for retailers. Among these I chose to implement:

1. The ability to add extra card ranges to the validation system to allow for special cards such as loyalty cards.
2. Validation of other range-based inputs such as voucher barcodes.
3. A fast and efficient lookup mechanism for determining the scheme for a PAN.

I believe I have achieved most of the functionality I planned to implement.

##### Data structures

I took the opportunity here to make use of an `Interval tree`. This structure works perfectly for fast lookups of PANS to find the matching scheme. This is a slightly incomplete implementation as the indexes are not used. My implementation does not cover adding and removing of ranges.
 
#### Proof of correctness

The task was to validate and classify a short list of PANS. The correctness of my solution can be seen in the `CardFactoryTest` class where each of the supplied PANs are clearly validated and matched with their schemes correctly.

#### Testing

There are a few small tests written in `Groovy` using the excellent `Spock` test framework.

I have made no attempt to maximise coverage of these tests. In a commercial environment this would be required.

#### Notes

I have use `Maven` for the build mechanism because I am most familliar with it, it is well supported and popular.

In places where I felt it appropriate I have used Java 8 streams for data manipulation.

I have taken a first-hit on which methods/fields should be secured for access. Improving this would be a requirement before final release to a real customer.

The `IntervalTree` class is copied from the internet but from [one of my answers](https://stackoverflow.com/a/25564351/823393) on StackOverflow. The code was originally written by me but not during the time of this task.
 
The `Scheme`s are currently hard-coded. These, along with the `UKRanges` would probably be better turned into configurable entities. The `UKRAnges` schemes were transcribed from [Barclays UK BIN rules](https://www.barclaycard.co.uk/business/files/BIN-Rules-UK.pdf).

The JavaDoc comments would need to be polished before releasing to a customer.

The coding took me two days to complete and test. This is probably quite quick for something as flexible but I did spend almost a week thinking about it and planning my approach.

Thank you for an entertaining weekend.
