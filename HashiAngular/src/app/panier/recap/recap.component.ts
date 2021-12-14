import {
  AfterContentChecked,
  ChangeDetectorRef,
  Component,
  OnInit,
} from "@angular/core";
import { Router } from "@angular/router";
import { LigneCarte } from "src/app/model/ligne-carte";

@Component({
  selector: "app-recap",
  templateUrl: "./recap.component.html",
  styleUrls: ["./recap.component.css"],
})
export class RecapComponent implements OnInit, AfterContentChecked {
  ligneCarte: LigneCarte[] = [];
  panier: Map<string, number> = new Map();
  total: number = 0;
  quantites: number[] = [];
  prixTotaux: number[] = [];

  constructor(
    private changeDetector: ChangeDetectorRef,
    private router: Router
  ) {
    this.panier = new Map(JSON.parse(localStorage.getItem("panier")!));
    this.panier.forEach((value: number, key: string) => {
      this.ligneCarte.push(JSON.parse(key));
    });
  }

  ngOnInit(): void {
    this.total = 0;
    this.ligneCarte.forEach((lc) => {
      this.quantites.push(this.panier.get(JSON.stringify(lc))!);
      this.prixTotaux.push(
        this.panier.get(JSON.stringify(lc))! * lc.id!.plat!.prix!
      );
      this.total += this.prixTotaux[this.prixTotaux.length - 1];
    });
  }

  getTotal() {
    return this.total;
  }

  ngAfterContentChecked(): void {
    this.changeDetector.detectChanges();
  }

  goValider() {
    // localStorage.setItem('valider', 'valider');
    // localStorage.setItem('totalPanier', this.total as unknown as string);
  }
}
