grammar TagGrammar;

 @members {
    int mode = 0;
//	protected int EOF = Eof; //https://groups.google.com/forum/#!topic/antlr-discussion/-Ko4w4qlDZM
}

tag : tagType TagTypeSeparator tagBody BodyIdSeparator userName dateTimeUtc EOF|
	 tagType TagTypeSeparator BodyIdSeparator userName dateTimeUtc EOF
;
endTag : endTagSign (freeText)? BodyIdSeparator userName dateTimeUtc EOF
;

tagBody : (freeText|att)+
;
freeText : FreeText
;
att : Identifier (BracetL FreeText BracetR)?
;
tagType : TagType
;
userName : UserName
;
dateTimeUtc : DateTimeUtc
;
endTagSign : EndTagSign
;
EndTagSign: {mode == 0}? '@<' {mode = 2;} 
;
TagType: {mode == 0}? Letter (Letter|[0-9])* {mode = 1;} 
;
TagTypeSeparator : {mode == 1}? WsFrag* (WsFrag|':') WsFrag* {mode = 2;} 
;
fragment
NumberSign : '#'
;
Identifier : {mode == 2}? NumberSign Letter (Letter|[0-9])*
;
BracetL: '('
;
BracetR: ')' 
;
FreeText: {mode == 2}? (~('(' | ')' | '#' | '|'))+
;
BodyIdSeparator: {mode == 2}? '|' {mode = 3;} 
;
DateTimeUtc:{mode == 3}? [0-9][0-9][0-9][0-9]'-'[0-9][0-9]'-'[0-9][0-9]'T'[0-9][0-9]':'[0-9][0-9]':'[0-9][0-9]'.'[0-9][0-9][0-9][0-9][0-9][0-9][0-9]'Z'
;
UserName:{mode == 3}? (~(' '|'\r'|'\t'|'\u000C'))+
;
fragment
Letter 	:	'A'..'Z'
	|	'a'..'z'
	|	'_'
;

fragment
WsFrag : (' '|'\r'|'\t'|'\u000C')
;
IgnoreWs  : {mode == 0 | mode == 2 | mode == 3}?  WsFrag -> skip
;