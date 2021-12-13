import { Plat } from './plat';
export class LigneCommandePk {
  private _plat?: Plat | undefined;

  /**
   * Getter plat
   * @return {Plat}
   */
  public get plat(): Plat | undefined {
    return this._plat;
  }

  /**
   * Setter plat
   * @param {Plat} value
   */
  public set plat(value: Plat | undefined) {
    this._plat = value;
  }
}
