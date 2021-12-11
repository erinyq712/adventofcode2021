use strict;
use File::Basename;

my($APP) = $0;

sub showHelp {
  print "Usage: perl " . basename($0) . " <name of input file>\n";
  exit 1;
}

sub trim {
  my ($arg) = @_;
  $arg =~ s/^\s+|\s+$//g;
  return $arg;
}

my($input) = $#ARGV >= 0 ? $ARGV[0] : "01input.txt";

if (! -f $input) {
    print STDERR "File does not exist: $input\n";
    exit 1;
}

my %stats;
$stats{INCREASED} = 0;
$stats{DECREASED} = 0;
$stats{UNCHANGED} = 0;

open(IN, "<$input");
my ($prev);
while(my $depth = <IN>) {
  $depth = trim($depth);
  if (defined $prev) {
    my $change = $depth - $prev;
    if ($change < 0) {
      print "$depth (decreased)\n";
      $stats{DECREASED}++;
    } elsif ($change > 0) {
      print "$depth (increased)\n";
      $stats{INCREASED}++;
    } else {
      print "$depth (unchanged)\n";
      $stats{UNCHANGED}++;
    }
  } else {
    print "$depth (N/A - no previous measurement)\n";
  }
  $prev = $depth;
}
print "Unchanged: $stats{UNCHANGED}\n";
print "Increased: $stats{INCREASED}\n";
print "Decreased: $stats{DECREASED}\n";

